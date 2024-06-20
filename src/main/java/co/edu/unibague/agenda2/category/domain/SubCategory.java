package co.edu.unibague.agenda2.category.domain;

import co.edu.unibague.agenda2.category.domain.valueobjects.CategoryName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class SubCategory {
    private final Id subCategoryId;
    private final CategoryName subCategoryName;
    private final Category categoryFather;

    public SubCategory(String subCategoryId, String subCategoryName, Category categoryFather) {
        this.subCategoryId = new Id(subCategoryId);
        this.subCategoryName = new CategoryName(subCategoryName);
        this.categoryFather = categoryFather;
    }

    public static SubCategory createSubCategory(String subCategoryId, String subCategoryName, Category categoryFather) {
        return new SubCategory(subCategoryId, subCategoryName, categoryFather);
    }

    public UUID getId() {
        return subCategoryId.value();
    }

    public String getName() {
        return subCategoryName.value();
    }

    public Category getFather() {
        return categoryFather;
    }
}
