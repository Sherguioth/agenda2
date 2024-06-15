package co.edu.unibague.agenda2.shared.infrastructure.api;

import co.edu.unibague.agenda2.shared.infrastructure.security.CustomUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailService userDetailService;

    public AuthController(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok().body(this.userDetailService.loginUser(authRequest));
    }
}
