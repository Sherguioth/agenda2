package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.category.domain.valueobjects.CategoryName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class Category {
    private final Id categoryId;
    private final CategoryName categoryName;

    public Category(String  categoryId, String categoryName) {
        this.categoryId = new Id(categoryId);
        this.categoryName = new CategoryName(categoryName);
    }

    public static Category createCategory(String categoryId, String categoryName) {
        return new Category(categoryId, categoryName);
    }

    public UUID getId() {
        return categoryId.value();
    }

    public String getName() {
        return categoryName.value();
    }
}
