package co.edu.unibague.agenda2.schedule.domain.usecases;

public interface UpdateSchedule {

    void addCategoryToSchedule(String scheduleId, String categoryName, boolean isMandatory);
}
