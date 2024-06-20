package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.category.domain.valueobjects.CategoryName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class Category {
    private final Id categoryId;
    private final CategoryName categoryName;

    public Category(String  id, String name) {
        this.categoryId = new Id(id);
        this.categoryName = new CategoryName(name);
    }

    public static Category createCategory(String  id, String name) {
        return new Category(id, name);
    }

    public UUID getId() {
        return categoryId.value();
    }

    public String getName() {
        return categoryName.value();
    }
}
