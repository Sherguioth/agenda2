package co.edu.unibague.agenda2.place.infrastructure.entities;

import co.edu.unibague.agenda2.place.domain.Place;

public class PlaceMapper {

    private PlaceMapper() {
    }

    public static PlaceEntity toPlaceEntity(Place place) {
        return new PlaceEntity(place.getId(), place.getPlaceName(), place.getPlaceAddress());
    }

    public static Place toDomainPlace(PlaceEntity placeEntity) {
        return Place.create(placeEntity.getId().toString(), placeEntity.getName(), placeEntity.getAddress());
    }
}
