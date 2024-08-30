package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.CategoryGroup;
import co.edu.unibague.agenda2.category.domain.CategoryGroupRepository;
import co.edu.unibague.agenda2.category.domain.usecases.CreateCategoryGroup;

public class CategoryGroupCreator implements CreateCategoryGroup {

    private final CategoryGroupRepository repository;

    public CategoryGroupCreator(CategoryGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createCategoryGroup(CategoryGroup categoryGroup) {
        repository.save(categoryGroup);
    }
}
