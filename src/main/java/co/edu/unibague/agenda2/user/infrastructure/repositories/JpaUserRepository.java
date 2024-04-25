package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
}
