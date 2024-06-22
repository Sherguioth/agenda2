package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.category.domain.SubCategoryRepository;
import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.UpdateSchedule;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.shared.domain.Id;

public class ScheduleUpdater implements UpdateSchedule {

    private final ScheduleRepository scheduleRepository;
    private final SubCategoryRepository subCategoryRepository;

    public ScheduleUpdater(ScheduleRepository scheduleRepository, SubCategoryRepository subCategoryRepository) {
        this.scheduleRepository = scheduleRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public void addCategoryToSchedule(String scheduleId, String categoryName, boolean isMandatory) {
        var optionalSchedule = scheduleRepository.findById(new Id(scheduleId));
        var optionalSubCategory = subCategoryRepository.findByName(categoryName);

        Schedule schedule = optionalSchedule.orElseThrow(() -> new RuntimeException("Schedule not found"));
        SubCategory subCategory = optionalSubCategory.orElseThrow(() -> new RuntimeException("SubCategory not found"));

        var scheduleCategory = new ScheduleCategory(subCategory, isMandatory);
        schedule.addCategory(scheduleCategory);
        scheduleRepository.addCategoryToSchedule(schedule, scheduleCategory);
    }
}
