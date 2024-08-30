package co.edu.unibague.agenda2.category.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    void save(Category category);

    List<Category> findAll();

    Optional<Category> findByName(String name);
}
