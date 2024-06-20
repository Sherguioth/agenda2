package co.edu.unibague.agenda2.category.application;

import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.category.domain.SubCategoryRepository;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveSubCategory;

import java.util.List;

public class SubCategoryRetriever implements RetrieveSubCategory {

    private final SubCategoryRepository repository;

    public SubCategoryRetriever(SubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return repository.findAll();
    }
}
