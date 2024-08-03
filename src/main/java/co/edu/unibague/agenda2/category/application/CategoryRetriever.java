package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategory;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public class CategoryRetriever implements RetrieveCategory {

    private final CategoryRepository repository;

    public CategoryRetriever(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Category> getCategory(String categoryId) {
        return repository.findById(new Id(categoryId));
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
