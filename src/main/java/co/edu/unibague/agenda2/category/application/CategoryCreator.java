package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.category.domain.usecases.CreateCategory;

public class CategoryCreator implements CreateCategory {

    private final CategoryRepository repository;

    public CategoryCreator(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createCategory(Category category) {
        repository.save(category);
    }
}
