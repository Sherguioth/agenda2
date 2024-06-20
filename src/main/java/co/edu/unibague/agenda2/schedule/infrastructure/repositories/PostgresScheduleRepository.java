package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresScheduleRepository implements ScheduleRepository {

    private final JpaScheduleRepository jpaRepository;

    public PostgresScheduleRepository(JpaScheduleRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Schedule schedule) {
        var scheduleEntity = ScheduleMapper.toScheduleEntity(schedule);
        jpaRepository.save(scheduleEntity);
        ScheduleMapper.toScheduleEntity(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return jpaRepository.findAll().stream().map(ScheduleMapper::toDomainSchedule).toList();
    }

    @Override
    public Optional<Schedule> findById(Id id) {
        return jpaRepository.findById(id.value()).map(ScheduleMapper::toDomainSchedule);
    }
}
