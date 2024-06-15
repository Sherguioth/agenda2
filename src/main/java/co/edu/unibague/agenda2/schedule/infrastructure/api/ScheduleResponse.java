package co.edu.unibague.agenda2.schedule.infrastructure.api;

import java.util.UUID;

public record ScheduleResponse(UUID id, String title, UUID professionalId) {
}
