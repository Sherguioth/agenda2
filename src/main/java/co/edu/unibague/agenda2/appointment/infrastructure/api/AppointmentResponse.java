package co.edu.unibague.agenda2.appointment.infrastructure.api;

import java.util.UUID;

public record AppointmentResponse(UUID id, String description, UUID userId, UUID sessionId) {
}
