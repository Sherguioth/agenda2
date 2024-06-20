package co.edu.unibague.agenda2.shared.infrastructure.api;

import co.edu.unibague.agenda2.shared.infrastructure.security.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailService userDetailService;

    public AuthController(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        var authResponse = this.userDetailService.loginUser(authRequest);
        log.info("Login successful. User logged {}", authResponse.username());
        return ResponseEntity.ok().body(authResponse);
    }
}
