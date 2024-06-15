package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.CreateSchedule;

public class ScheduleCreator implements CreateSchedule {

    private final ScheduleRepository scheduleRepository;

    public ScheduleCreator(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
}
