package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.SubCategoryMapper;
import co.edu.unibague.agenda2.category.infrastructure.repositories.JpaSubCategoryRepository;
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
    private final JpaSubCategoryRepository jpaSubCategoryRepository;

    public PostgresScheduleRepository(JpaScheduleRepository jpaScheduleRepository,
                                      JpaScheduleCategoryRepository jpaScheduleCategoryRepository,
                                      JpaSubCategoryRepository jpaSubCategoryRepository) {
        this.jpaScheduleRepository = jpaScheduleRepository;
        this.jpaScheduleCategoryRepository = jpaScheduleCategoryRepository;
        this.jpaSubCategoryRepository = jpaSubCategoryRepository;
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
            var subCategory = SubCategoryMapper.toDomainSubCategory(
                    jpaSubCategoryRepository.findById(scheduleCategoryEntity.getCategoryEntity().getId()).orElseThrow()
            );

            scheduleCategories.add(new ScheduleCategory(subCategory, scheduleCategoryEntity.isMandatory()));
        });

        schedule.addCategories(scheduleCategories);
        return Optional.of(new ScheduleCategories(scheduleCategories));
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

    @Override
    public void removeCategoryFromSchedule(Schedule schedule, ScheduleCategory category) {
        var subCategory = category.getCategory();

        var scheduleCategoryEntityOptional = jpaScheduleCategoryRepository.findByScheduleEntityAndCategoryEntity(
                ScheduleMapper.toScheduleEntity(schedule),
                SubCategoryMapper.toSubCategoryEntity(subCategory)
        );

        scheduleCategoryEntityOptional.ifPresent(jpaScheduleCategoryRepository::delete);
    }
}
