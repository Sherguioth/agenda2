package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.category.domain.valueobjects.CategoryName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.Objects;
import java.util.UUID;

public class Category {
    private final Id categoryId;
    private final CategoryName categoryName;
    private final CategoryGroup father;

    public Category(String id, String name, CategoryGroup categoryGroup) {
        this.categoryId = new Id(id);
        this.categoryName = new CategoryName(name);
        this.father = categoryGroup;
    }

    public static Category createCategory(String id, String name, CategoryGroup categoryGroup) {
        return new Category(id, name, categoryGroup);
    }

    public UUID getId() {
        return categoryId.value();
    }

    public String getName() {
        return categoryName.value();
    }

    public CategoryGroup getCategoryGroup() {
        return father;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName);
    }
}
