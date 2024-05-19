package co.edu.unibague.agenda2.schedule.domain.usecases;

import co.edu.unibague.agenda2.schedule.domain.Schedule;

import java.util.List;

public interface RetrieveSchedule {

    List<Schedule> getAllSchedules();
}
