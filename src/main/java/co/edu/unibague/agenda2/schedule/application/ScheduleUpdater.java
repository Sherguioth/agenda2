package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.UpdateSchedule;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.shared.domain.Id;

public class ScheduleUpdater implements UpdateSchedule {

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;

    public ScheduleUpdater(ScheduleRepository scheduleRepository, CategoryRepository categoryRepository) {
        this.scheduleRepository = scheduleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategoryToSchedule(String scheduleId, String categoryName, boolean isMandatory) {
        var optionalSchedule = scheduleRepository.findById(new Id(scheduleId));
        var optionalCategory = categoryRepository.findByName(categoryName);

        Schedule schedule = optionalSchedule.orElseThrow(() -> new RuntimeException("Schedule not found"));
        Category category = optionalCategory.orElseThrow(() -> new RuntimeException("Category not found"));

        var scheduleCategory = new ScheduleCategory(category, isMandatory);
        schedule.addCategory(scheduleCategory);
        scheduleRepository.addCategoryToSchedule(schedule, scheduleCategory);
    }

    @Override
    public void removeCategoryFromSchedule(String scheduleId, String categoryName) {
        var sategoryOptional = categoryRepository.findByName(categoryName);
        Category category = sategoryOptional.orElseThrow(() -> new RuntimeException("Category not found"));

        var optionalSchedule = scheduleRepository.findById(new Id(scheduleId));
        Schedule schedule = optionalSchedule.orElseThrow(() -> new RuntimeException("Schedule not found"));

        var scheduleCategories = scheduleRepository.findScheduleCategories(schedule)
                .orElseThrow(() -> new RuntimeException("ScheduleCategory not found"));

        var scheduleCategory = scheduleCategories.value().stream()
                .filter(scheduleCat -> scheduleCat.value().getId().equals(category.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("ScheduleCategory not found"));

        schedule.removeCategory(scheduleCategory);
        scheduleRepository.removeCategoryFromSchedule(schedule, scheduleCategory);
    }
}
