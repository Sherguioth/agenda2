package co.edu.unibague.agenda2.place.infrastructure.repositories;

import co.edu.unibague.agenda2.place.infrastructure.entities.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPlaceRepository extends JpaRepository<PlaceEntity, UUID> {
}
