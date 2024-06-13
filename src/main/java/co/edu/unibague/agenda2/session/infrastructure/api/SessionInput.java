package co.edu.unibague.agenda2.session.infrastructure.api;

public record SessionInput(String id, String description, String dateTime, String scheduleId, String placeId) {
}
