package co.edu.unibague.agenda2.category.domain.usecases;

import co.edu.unibague.agenda2.category.domain.Category;

import java.util.List;

public interface RetrieveCategory {
    List<Category> getAllCategories();
}
