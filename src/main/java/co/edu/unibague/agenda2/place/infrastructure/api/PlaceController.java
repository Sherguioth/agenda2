package co.edu.unibague.agenda2.place.infrastructure.api;

import co.edu.unibague.agenda2.place.domain.Place;
import co.edu.unibague.agenda2.place.domain.usecases.CreatePlace;
import co.edu.unibague.agenda2.place.domain.usecases.RetrievePlace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final CreatePlace placeCreator;
    private final RetrievePlace placeRetriever;

    public PlaceController(CreatePlace placeCreator, RetrievePlace placeRetriever) {
        this.placeCreator = placeCreator;
        this.placeRetriever = placeRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createPlace(@RequestBody PlaceInput placeInput) {
        placeCreator.createPlace(Place.create(placeInput.id(), placeInput.name(), placeInput.address()));
        log.info("Place created: {}", placeInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getPlaces() {
        List<Place> placesList = placeRetriever.getAllPlaces();
        List<PlaceResponse> placeResponses = placesList.stream().map(
                place -> new PlaceResponse(place.getId(), place.getPlaceName(), place.getPlaceAddress())).toList();
        return ResponseEntity.ok().body(placeResponses);
    }
}
