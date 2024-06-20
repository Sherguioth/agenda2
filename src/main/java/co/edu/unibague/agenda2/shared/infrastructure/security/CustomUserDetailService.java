package co.edu.unibague.agenda2.shared.infrastructure.security;

import co.edu.unibague.agenda2.shared.infrastructure.api.AuthRequest;
import co.edu.unibague.agenda2.shared.infrastructure.api.AuthResponse;
import co.edu.unibague.agenda2.shared.infrastructure.utils.JwtUtils;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailService(JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with the email " + username + " doesn't exist"));
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase())));
        return new CustomUserDetails(user.getEmail(), user.getPassword(), authorities);
    }

    public AuthResponse loginUser(AuthRequest authRequest) {
        var username = authRequest.username();
        var password = authRequest.password();

        Authentication auth = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(auth);
        var token = jwtUtils.generateToken(auth);
        return new AuthResponse(username, "User logged in successfully", token);
    }

    private Authentication authenticate(String username, String password) {
        var userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password.");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
