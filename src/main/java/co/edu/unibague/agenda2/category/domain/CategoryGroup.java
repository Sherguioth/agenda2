package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.category.domain.valueobjects.CategoryGroupName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class CategoryGroup {
    private final Id categoryGroupId;
    private final CategoryGroupName categoryGroupName;

    public CategoryGroup(String  id, String name) {
        this.categoryGroupId = new Id(id);
        this.categoryGroupName = new CategoryGroupName(name);
    }

    public static CategoryGroup createCategoryGroup(String  id, String name) {
        return new CategoryGroup(id, name);
    }

    public UUID getId() {
        return categoryGroupId.value();
    }

    public String getName() {
        return categoryGroupName.value();
    }
}
