package co.edu.unibague.agenda2.schedule.infrastructure.repositories;

import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {
}
