package co.edu.unibague.agenda2.user.infrastructure.api;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(
        UUID id, String email, String password, String firstName, String lastName, LocalDate birthday) {
}
