package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaScheduleCategoryRepository extends JpaRepository<ScheduleCategoryEntity, UUID> {
}
