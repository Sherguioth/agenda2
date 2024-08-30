package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.RetrieveSchedule;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScheduleRetriever implements RetrieveSchedule {

    private final ScheduleRepository repository;

    public ScheduleRetriever(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

    @Override
    public Optional<Schedule> getSchedule(String scheduleId) {
        return repository.findById(new Id(scheduleId));
    }

    @Override
    public List<Schedule> getSchedulesByUserCategories(User user) {
        var userCategories = user.getCategories().stream().map(UserCategory::value)
                .collect(Collectors.toSet());
        var schedules = getAllSchedules();
        var schedulesResult = new ArrayList<Schedule>();

        for (var schedule : schedules) {
            if (hasCategoriesMandatory(schedule)) {
                var categoriesMandatory = schedule.getMandatoryCategories().stream()
                        .map(ScheduleCategory::value).collect(Collectors.toSet());
                if (userCategories.containsAll(categoriesMandatory)) {
                    schedulesResult.add(schedule);
                }
            } else {
                var categories = schedule.getCategories().stream().map(ScheduleCategory::value)
                        .collect(Collectors.toSet());
                if (categories.stream().anyMatch(userCategories::contains)) {
                    schedulesResult.add(schedule);
                }
            }
        }

        return schedulesResult;
    }

    private boolean hasCategoriesMandatory(Schedule schedule) {
        return schedule.getMandatoryCategories() != null && !schedule.getMandatoryCategories().isEmpty();
    }

}
