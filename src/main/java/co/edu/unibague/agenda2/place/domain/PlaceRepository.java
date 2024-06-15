package co.edu.unibague.agenda2.place.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository {

    void save(Place place);

    List<Place> findAll();

    Optional<Place> findById(Id id);
}
