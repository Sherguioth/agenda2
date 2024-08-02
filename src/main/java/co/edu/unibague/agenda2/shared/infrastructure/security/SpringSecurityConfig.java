package co.edu.unibague.agenda2.shared.infrastructure.security;

import co.edu.unibague.agenda2.shared.infrastructure.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final JwtUtils jwtUtils;

    public SpringSecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Public endpoints
                    http.requestMatchers(HttpMethod.GET, "/").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll();

                    // Private endpoints
                    http.requestMatchers(HttpMethod.POST, "/api/users").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/users").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/users/role").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/users/category").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/users/category").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/roles").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/roles").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/schedules").hasAnyRole("DEVELOPER", "ADMIN", "PROFESSIONAL");
                    http.requestMatchers(HttpMethod.GET, "/api/schedules").authenticated();
                    http.requestMatchers(HttpMethod.PUT, "/api/schedules/category").hasAnyRole("DEVELOPER", "ADMIN", "PROFESSIONAL");
                    http.requestMatchers(HttpMethod.POST, "/api/places").hasAnyRole("DEVELOPER", "ADMIN", "PROFESSIONAL");
                    http.requestMatchers(HttpMethod.GET, "/api/places").authenticated();
                    http.requestMatchers(HttpMethod.POST, "/api/sessions").hasAnyRole("DEVELOPER", "ADMIN", "PROFESSIONAL");
                    http.requestMatchers(HttpMethod.GET, "/api/sessions").authenticated();
                    http.requestMatchers(HttpMethod.POST, "/api/appointments").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/appointments").authenticated();
                    http.requestMatchers(HttpMethod.PUT, "/api/appointments").authenticated();
                    http.requestMatchers(HttpMethod.DELETE, "/api/appointments/**").authenticated();
                    http.requestMatchers(HttpMethod.POST, "/api/categories").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/categories").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/subcategories").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/subcategories").hasAnyRole("DEVELOPER", "ADMIN");

                    // Anyone else endpoint
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtValidatorFilter(jwtUtils), BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
