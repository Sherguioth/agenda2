package co.edu.unibague.agenda2.schedule.infrastructure.api;

public record ScheduleCategoryInput(String scheduleId, String categoryName, boolean isMandatory) {
}
