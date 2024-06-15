package co.edu.unibague.agenda2.session.infrastructure.api;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionResponse(UUID id, String description, LocalDateTime dateTime, UUID scheduleId, String scheduleTitle,
                              UUID placeId, String name) {
}
