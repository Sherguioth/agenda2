package co.edu.unibague.agenda2.category.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCategoryGroupRepository extends JpaRepository<CategoryGroupEntity, UUID> {
}
