package co.edu.unibague.agenda2.schedule.domain;

import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategories;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    void save(Schedule schedule);

    List<Schedule> findAll();

    Optional<Schedule> findById(Id id);

    Optional<ScheduleCategories> findScheduleCategories(Schedule schedule);

    void addCategoryToSchedule(Schedule schedule, ScheduleCategory scheduleCategory);

    void removeCategoryFromSchedule(Schedule schedule, ScheduleCategory scheduleCategory);
}
