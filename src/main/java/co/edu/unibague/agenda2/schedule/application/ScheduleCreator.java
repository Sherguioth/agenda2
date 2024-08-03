package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.CreateSchedule;

public class ScheduleCreator implements CreateSchedule {

    private final ScheduleRepository repository;

    public ScheduleCreator(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createSchedule(Schedule schedule) {
        repository.save(schedule);
    }
}
