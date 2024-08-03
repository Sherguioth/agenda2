package co.edu.unibague.agenda2.appointment.infrastructure.api;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentSummary(UUID id, String description, String sessionDescription, LocalDateTime dateTime,
                                 int usersLimit, UUID placeId, String placeName, String placeAddress) {
}
