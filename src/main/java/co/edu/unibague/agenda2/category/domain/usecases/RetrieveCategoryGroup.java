package co.edu.unibague.agenda2.category.domain.usecases;

import co.edu.unibague.agenda2.category.domain.CategoryGroup;

import java.util.List;
import java.util.Optional;

public interface RetrieveCategoryGroup {

    Optional<CategoryGroup> getCategoryGroup(String categoryGroupId);

    List<CategoryGroup> getAllCategoryGroups();
}
