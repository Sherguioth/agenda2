package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    void save(Category category);

    List<Category> findAll();

    Optional<Category> findById(Id id);
}
