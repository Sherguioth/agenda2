package co.edu.unibague.agenda2.category.domain;

import java.util.List;

public interface SubCategoryRepository {

    void save(SubCategory subCategory);

    List<SubCategory> findAll();
}
