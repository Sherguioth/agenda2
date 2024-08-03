package co.edu.unibague.agenda2.category.domain.usecases;

import co.edu.unibague.agenda2.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface RetrieveCategory {

    Optional<Category> getCategory(String categoryId);

    List<Category> getAllCategories();
}
