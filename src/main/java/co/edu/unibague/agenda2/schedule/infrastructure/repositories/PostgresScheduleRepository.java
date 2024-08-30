package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryMapper;
import co.edu.unibague.agenda2.category.infrastructure.repositories.JpaCategoryRepository;
import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategories;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleCategoryEntity;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostgresScheduleRepository implements ScheduleRepository {

    private final JpaScheduleRepository jpaScheduleRepository;
    private final JpaScheduleCategoryRepository jpaScheduleCategoryRepository;
    private final JpaCategoryRepository jpaCategoryRepository;

    public PostgresScheduleRepository(JpaScheduleRepository jpaScheduleRepository,
                                      JpaScheduleCategoryRepository jpaScheduleCategoryRepository,
                                      JpaCategoryRepository jpaCategoryRepository) {
        this.jpaScheduleRepository = jpaScheduleRepository;
        this.jpaScheduleCategoryRepository = jpaScheduleCategoryRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
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
    public Optional<ScheduleCategories> findScheduleCategories(Schedule schedule) {
        var scheduleEntity = jpaScheduleRepository.findById(schedule.getId())
                .orElseThrow(() -> new RuntimeException("Schedule with id " + schedule.getId() + " not found"));

        List<ScheduleCategoryEntity> scheduleCategoryEntities = jpaScheduleCategoryRepository.findAllByScheduleEntity(scheduleEntity);
        Set<ScheduleCategory> scheduleCategories = new HashSet<>();

        scheduleCategoryEntities.forEach(scheduleCategoryEntity -> {
            var category = CategoryMapper.toDomainCategory(
                    jpaCategoryRepository.findById(scheduleCategoryEntity.getCategoryEntity().getId()).orElseThrow()
            );

            scheduleCategories.add(new ScheduleCategory(category, scheduleCategoryEntity.isMandatory()));
        });

        schedule.addCategories(scheduleCategories);
        return Optional.of(new ScheduleCategories(scheduleCategories));
    }

    @Override
    public void addCategoryToSchedule(Schedule schedule, ScheduleCategory scheduleCategory) {
        var scheduleEntity = ScheduleMapper.toScheduleEntity(schedule);
        var categoryEntity = CategoryMapper.toCategoryEntity(scheduleCategory.value());

        var scheduleCategoryEntity = new ScheduleCategoryEntity(
                UUID.randomUUID(),
                scheduleCategory.isMandatory(),
                categoryEntity,
                scheduleEntity
        );

        jpaScheduleCategoryRepository.save(scheduleCategoryEntity);
    }

    @Override
    public void removeCategoryFromSchedule(Schedule schedule, ScheduleCategory scheduleCategory) {
        var category = scheduleCategory.value();

        var scheduleCategoryEntityOptional = jpaScheduleCategoryRepository.findByScheduleEntityAndCategoryEntity(
                ScheduleMapper.toScheduleEntity(schedule),
                CategoryMapper.toCategoryEntity(category)
        );

        scheduleCategoryEntityOptional.ifPresent(jpaScheduleCategoryRepository::delete);
    }
}
