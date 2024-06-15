package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaUserRoleRepository extends JpaRepository<UserRoleEntity, UUID> {
    List<UserRoleEntity> findAllByUserId(UserEntity userId);
}
