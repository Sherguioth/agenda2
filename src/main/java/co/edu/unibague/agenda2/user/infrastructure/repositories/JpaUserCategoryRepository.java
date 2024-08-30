package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryEntity;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserCategoryEntity;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserCategoryRepository extends JpaRepository<UserCategoryEntity, Long> {

    List<UserCategoryEntity> findAllByUserEntity(UserEntity userEntity);

    Optional<UserCategoryEntity> findByUserEntityAndCategoryEntity(UserEntity userEntity, CategoryEntity category);
}
