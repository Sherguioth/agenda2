package co.edu.unibague.agenda2.place.application;

import co.edu.unibague.agenda2.place.domain.Place;
import co.edu.unibague.agenda2.place.domain.PlaceRepository;
import co.edu.unibague.agenda2.place.domain.usecases.RetrievePlace;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public class PlaceRetriever implements RetrievePlace {

    private final PlaceRepository placeRepository;

    public PlaceRetriever(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> getAllPlaces() {
        return this.placeRepository.findAll();
    }

    @Override
    public Optional<Place> getPlace(String placeId) {
        return placeRepository.findById(new Id(placeId));
    }
}
