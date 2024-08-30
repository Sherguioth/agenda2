package co.edu.unibague.agenda2.category.infrastructure.entities;

import co.edu.unibague.agenda2.category.domain.Category;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryEntity toCategoryEntity(Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getName(),
                CategoryGroupMapper.toCategoryGroupEntity(category.getCategoryGroup())
        );
    }

    public static Category toDomainCategory(CategoryEntity categoryEntity) {
        return Category.createCategory(
                categoryEntity.getId().toString(),
                categoryEntity.getName(),
                CategoryGroupMapper.toDomainCategoryGroup(categoryEntity.getCategoryGroupEntity())
        );
    }
}
