package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.RetrieveSchedule;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ScheduleRetriever implements RetrieveSchedule {

    private final ScheduleRepository scheduleRepository;

    public ScheduleRetriever(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> getSchedule(String scheduleId) {
        return scheduleRepository.findById(new Id(scheduleId));
    }
}
