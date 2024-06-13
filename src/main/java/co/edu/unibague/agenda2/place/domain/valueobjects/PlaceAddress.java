package co.edu.unibague.agenda2.place.domain.valueobjects;

public class PlaceAddress {

    private final String placeAddress;

    public PlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String value() {
        return this.placeAddress;
    }
}
