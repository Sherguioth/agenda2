package co.edu.unibague.agenda2.shared.infrastructure.api;

import java.util.List;

public record AuthResponse(String username, String message, String token, List<String> roles) {
}
