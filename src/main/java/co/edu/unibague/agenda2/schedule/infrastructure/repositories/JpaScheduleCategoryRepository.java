package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.SubCategoryEntity;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleCategoryEntity;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaScheduleCategoryRepository extends JpaRepository<ScheduleCategoryEntity, UUID> {
    List<ScheduleCategoryEntity> findAllByScheduleEntity(ScheduleEntity scheduleEntity);

    Optional<ScheduleCategoryEntity> findByScheduleEntityAndCategoryEntity(ScheduleEntity schedule, SubCategoryEntity category);
}
