package co.edu.unibague.agenda2.place.domain;

import co.edu.unibague.agenda2.place.domain.valueobjects.PlaceAddress;
import co.edu.unibague.agenda2.place.domain.valueobjects.PlaceName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class Place {

    private final Id placeId;
    private final PlaceName placeName;
    private PlaceAddress placeAddress;

    public Place(String  placeId, String placeName, String placeAddress) {
        this.placeId = new Id(placeId);
        this.placeName = new PlaceName(placeName);
        this.placeAddress = new PlaceAddress(placeAddress);
    }

    public static Place create(String placeId, String placeName, String placeAddress) {
        return new Place(placeId, placeName, placeAddress);
    }

    public UUID getId() {
        return placeId.value();
    }

    public String getPlaceName() {
        return placeName.value();
    }

    public String  getPlaceAddress() {
        return placeAddress.value();
    }
}
