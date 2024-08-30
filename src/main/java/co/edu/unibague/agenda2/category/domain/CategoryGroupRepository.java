package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface CategoryGroupRepository {

    void save(CategoryGroup categoryGroup);

    List<CategoryGroup> findAll();

    Optional<CategoryGroup> findById(Id id);
}
