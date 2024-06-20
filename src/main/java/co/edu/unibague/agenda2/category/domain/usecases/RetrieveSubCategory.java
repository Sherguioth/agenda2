package co.edu.unibague.agenda2.category.domain.usecases;

import co.edu.unibague.agenda2.category.domain.SubCategory;

import java.util.List;

public interface RetrieveSubCategory {
    List<SubCategory> getAllSubCategories();
}
