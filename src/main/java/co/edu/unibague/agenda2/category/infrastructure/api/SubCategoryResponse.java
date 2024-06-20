package co.edu.unibague.agenda2.category.infrastructure.api;

import java.util.UUID;

public record SubCategoryResponse(UUID id, String name, UUID categoryId, String categoryName) {
}
