package co.edu.unibague.agenda2.role.infrastructure.repositories;

import co.edu.unibague.agenda2.role.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {
}
