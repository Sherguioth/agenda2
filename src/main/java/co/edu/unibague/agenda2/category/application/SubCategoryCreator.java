package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.category.domain.SubCategoryRepository;
import co.edu.unibague.agenda2.category.domain.usecases.CreateSubCategory;

public class SubCategoryCreator implements CreateSubCategory {

    private final SubCategoryRepository repository;

    public SubCategoryCreator(SubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createSubCategory(SubCategory subCategory) {
        repository.save(subCategory);
    }
}
