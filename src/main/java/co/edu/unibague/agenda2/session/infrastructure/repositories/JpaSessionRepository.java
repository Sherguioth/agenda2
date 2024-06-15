package co.edu.unibague.agenda2.session.infrastructure.repositories;

import co.edu.unibague.agenda2.session.infrastructure.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaSessionRepository extends JpaRepository<SessionEntity, UUID> {
}
