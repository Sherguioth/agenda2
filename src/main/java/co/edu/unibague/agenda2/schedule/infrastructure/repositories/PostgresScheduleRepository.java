package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresScheduleRepository implements ScheduleRepository {

    private final JpaScheduleRepository scheduleRepository;

    public PostgresScheduleRepository(JpaScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void save(Schedule schedule) {
        var scheduleEntity = ScheduleMapper.toScheduleEntity(schedule);
        scheduleRepository.save(scheduleEntity);
        ScheduleMapper.toScheduleEntity(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleMapper::toDomainSchedule).toList();
    }
}
