package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.SubCategoryMapper;
import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleCategoryEntity;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresScheduleRepository implements ScheduleRepository {

    private final JpaScheduleRepository jpaScheduleRepository;
    private final JpaScheduleCategoryRepository jpaScheduleCategoryRepository;

    public PostgresScheduleRepository(JpaScheduleRepository jpaScheduleRepository,
                                      JpaScheduleCategoryRepository jpaScheduleCategoryRepository) {
        this.jpaScheduleRepository = jpaScheduleRepository;
        this.jpaScheduleCategoryRepository = jpaScheduleCategoryRepository;
    }

    @Override
    public void save(Schedule schedule) {
        var scheduleEntity = ScheduleMapper.toScheduleEntity(schedule);
        jpaScheduleRepository.save(scheduleEntity);
        ScheduleMapper.toScheduleEntity(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return jpaScheduleRepository.findAll().stream().map(ScheduleMapper::toDomainSchedule).toList();
    }

    @Override
    public Optional<Schedule> findById(Id id) {
        return jpaScheduleRepository.findById(id.value()).map(ScheduleMapper::toDomainSchedule);
    }

    @Override
    public void addCategoryToSchedule(Schedule schedule, ScheduleCategory category) {
        var scheduleEntity = ScheduleMapper.toScheduleEntity(schedule);
        var subCategoryEntity = SubCategoryMapper.toSubCategoryEntity(category.getCategory());

        var scheduleCategoryEntity = new ScheduleCategoryEntity(
                UUID.randomUUID(),
                category.isMandatory(),
                subCategoryEntity,
                scheduleEntity
        );

        jpaScheduleCategoryRepository.save(scheduleCategoryEntity);
    }
}
