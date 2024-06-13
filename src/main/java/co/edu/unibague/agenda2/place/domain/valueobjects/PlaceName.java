package co.edu.unibague.agenda2.place.domain.valueobjects;

public class PlaceName {

    private final String placeName;

    public PlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String value() {
        return this.placeName;
    }
}
