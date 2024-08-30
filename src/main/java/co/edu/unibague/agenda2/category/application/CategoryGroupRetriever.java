package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.CategoryGroup;
import co.edu.unibague.agenda2.category.domain.CategoryGroupRepository;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategoryGroup;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public class CategoryGroupRetriever implements RetrieveCategoryGroup {

    private final CategoryGroupRepository repository;

    public CategoryGroupRetriever(CategoryGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CategoryGroup> getCategoryGroup(String categoryGroupId) {
        return repository.findById(new Id(categoryGroupId));
    }

    @Override
    public List<CategoryGroup> getAllCategoryGroups() {
        return repository.findAll();
    }
}
