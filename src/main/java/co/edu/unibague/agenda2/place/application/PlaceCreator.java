package co.edu.unibague.agenda2.place.application;

import co.edu.unibague.agenda2.place.domain.Place;
import co.edu.unibague.agenda2.place.domain.PlaceRepository;
import co.edu.unibague.agenda2.place.domain.usecases.CreatePlace;

public class PlaceCreator implements CreatePlace {

    private final PlaceRepository repository;

    public PlaceCreator(PlaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPlace(Place place) {
        repository.save(place);
    }
}
