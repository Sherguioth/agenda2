package co.edu.unibague.agenda2.category.infrastructure.entities;

import co.edu.unibague.agenda2.category.domain.CategoryGroup;

public class CategoryGroupMapper {

    private CategoryGroupMapper() {
    }

    public static CategoryGroupEntity toCategoryGroupEntity(CategoryGroup categoryGroup) {
        return new CategoryGroupEntity(categoryGroup.getId(), categoryGroup.getName());
    }

    public static CategoryGroup toDomainCategoryGroup(CategoryGroupEntity categoryGroupEntity) {
        return CategoryGroup.createCategoryGroup(categoryGroupEntity.getId().toString(), categoryGroupEntity.getName());
    }
}
