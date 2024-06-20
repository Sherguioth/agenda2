package co.edu.unibague.agenda2.session.infrastructure.api;

public record SessionInput(String id, String description, String dateTime, int usersLimit, String scheduleId, String placeId) {
}
