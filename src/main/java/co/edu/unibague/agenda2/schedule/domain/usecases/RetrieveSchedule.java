package co.edu.unibague.agenda2.schedule.domain.usecases;

import co.edu.unibague.agenda2.schedule.domain.Schedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RetrieveSchedule {

    List<Schedule> getAllSchedules();

    Optional<Schedule> getSchedule(String userId);
}
