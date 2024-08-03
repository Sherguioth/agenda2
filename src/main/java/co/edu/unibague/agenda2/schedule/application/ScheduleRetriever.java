package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.usecases.RetrieveSchedule;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

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
}
