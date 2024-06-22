package co.edu.unibague.agenda2.category.domain;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository {

    void save(SubCategory subCategory);

    List<SubCategory> findAll();

    Optional<SubCategory> findByName(String name);
}
