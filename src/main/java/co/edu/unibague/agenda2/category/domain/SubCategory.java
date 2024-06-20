package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.category.domain.valueobjects.CategoryName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class SubCategory {
    private final Id subCategoryId;
    private final CategoryName subCategoryName;
    private final Category father;

    public SubCategory(String id, String name, Category category) {
        this.subCategoryId = new Id(id);
        this.subCategoryName = new CategoryName(name);
        this.father = category;
    }

    public static SubCategory createSubCategory(String id, String name, Category category) {
        return new SubCategory(id, name, category);
    }

    public UUID getId() {
        return subCategoryId.value();
    }

    public String getName() {
        return subCategoryName.value();
    }

    public Category getCategory() {
        return father;
    }
}
