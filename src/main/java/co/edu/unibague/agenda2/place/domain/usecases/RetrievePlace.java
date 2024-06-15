package co.edu.unibague.agenda2.place.domain.usecases;

import co.edu.unibague.agenda2.place.domain.Place;

import java.util.List;
import java.util.Optional;

public interface RetrievePlace {

    List<Place> getAllPlaces();

    Optional<Place> getPlace(String placeId);
}

