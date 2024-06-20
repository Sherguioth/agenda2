package co.edu.unibague.agenda2.place.infrastructure.repositories;

import co.edu.unibague.agenda2.place.domain.Place;
import co.edu.unibague.agenda2.place.domain.PlaceRepository;

import co.edu.unibague.agenda2.place.infrastructure.entities.PlaceMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresPlaceRepository implements PlaceRepository {

    private final JpaPlaceRepository jpaPlaceRepository;

    public PostgresPlaceRepository(JpaPlaceRepository jpaPlaceRepository) {
        this.jpaPlaceRepository = jpaPlaceRepository;
    }


    @Override
    public void save(Place place) {
        var placeEntity = PlaceMapper.toPlaceEntity(place);
        var savedPlace = jpaPlaceRepository.save(placeEntity);
        PlaceMapper.toDomainPlace(savedPlace);
    }

    @Override
    public List<Place> findAll() {
        return jpaPlaceRepository.findAll().stream().map(PlaceMapper::toDomainPlace).toList();
    }

    @Override
    public Optional<Place> findById(Id id) {
        return jpaPlaceRepository.findById(id.value()).map(PlaceMapper::toDomainPlace);
    }
}
