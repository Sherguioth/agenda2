package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategory;

import java.util.List;

public class CategoryRetriever implements RetrieveCategory {

    private final CategoryRepository repository;

    public CategoryRetriever(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
