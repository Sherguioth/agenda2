package co.edu.unibague.agenda2.category.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaSubCategoryRepository extends JpaRepository<SubCategoryEntity, UUID> {
}
