package co.edu.unibague.agenda2.place.domain;

import co.edu.unibague.agenda2.place.domain.valueobjects.PlaceAddress;
import co.edu.unibague.agenda2.place.domain.valueobjects.PlaceName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class Place {

    private final Id placeId;
    private final PlaceName placeName;
    private PlaceAddress placeAddress;

    public Place(String id, String name, String address) {
        this.placeId = new Id(id);
        this.placeName = new PlaceName(name);
        this.placeAddress = new PlaceAddress(address);
    }

    public static Place create(String id, String name, String address) {
        return new Place(id, name, address);
    }

    public UUID getId() {
        return placeId.value();
    }

    public String getPlaceName() {
        return placeName.value();
    }

    public String getPlaceAddress() {
        return placeAddress.value();
    }
}
