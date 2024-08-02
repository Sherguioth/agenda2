package co.edu.unibague.agenda2.schedule.domain;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategories;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository {

    void save(Schedule schedule);

    List<Schedule> findAll();

    Optional<Schedule> findById(Id id);

    Optional<ScheduleCategories> findScheduleCategories(Schedule schedule);

    void addCategoryToSchedule(Schedule schedule, ScheduleCategory category);

    void removeCategoryFromSchedule(Schedule schedule, ScheduleCategory category);
}
