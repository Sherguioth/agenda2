package co.edu.unibague.agenda2.appointment.infrastructure.api;

public record AppointmentInput(String id, String description, String userId, String sessionId) {
}
