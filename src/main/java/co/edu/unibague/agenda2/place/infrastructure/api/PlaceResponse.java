package co.edu.unibague.agenda2.place.infrastructure.api;

import java.util.UUID;

public record PlaceResponse(UUID id, String name, String address) {
}
