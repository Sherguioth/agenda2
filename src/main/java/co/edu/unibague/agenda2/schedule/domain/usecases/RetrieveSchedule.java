package co.edu.unibague.agenda2.schedule.domain.usecases;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface RetrieveSchedule {

    List<Schedule> getAllSchedules();

    Optional<Schedule> getSchedule(String userId);

    List<Schedule> getSchedulesByUserCategories(User user);
}
