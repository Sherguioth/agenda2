package co.edu.unibague.agenda2.category.infrastructure.entities;

import co.edu.unibague.agenda2.category.domain.SubCategory;

public class SubCategoryMapper {

    private SubCategoryMapper() {
    }

    public static SubCategoryEntity toSubCategoryEntity(SubCategory subCategory) {
        return new SubCategoryEntity(
                subCategory.getId(),
                subCategory.getName(),
                CategoryMapper.toCategoryEntity(subCategory.getFather())
        );
    }

    public static SubCategory toDomainSubCategory(SubCategoryEntity subCategoryEntity) {
        return SubCategory.createSubCategory(
                subCategoryEntity.getId().toString(),
                subCategoryEntity.getName(),
                CategoryMapper.toDomainCategory(subCategoryEntity.getCategoryFather())
        );
    }
}
