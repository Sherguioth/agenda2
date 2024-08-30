package co.edu.unibague.agenda2.category.infrastructure.repositories;

import co.edu.unibague.agenda2.category.domain.CategoryGroup;
import co.edu.unibague.agenda2.category.domain.CategoryGroupRepository;
import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryGroupMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresCategoryGroupRepository implements CategoryGroupRepository {

    private final JpaCategoryGroupRepository jpaRepository;

    public PostgresCategoryGroupRepository(JpaCategoryGroupRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CategoryGroup categoryGroup) {
        var categoryEntity = CategoryGroupMapper.toCategoryGroupEntity(categoryGroup);
        var savedCategoryEntity = jpaRepository.save(categoryEntity);
        CategoryGroupMapper.toDomainCategoryGroup(savedCategoryEntity);
    }

    @Override
    public List<CategoryGroup> findAll() {
        return jpaRepository.findAll().stream().map(CategoryGroupMapper::toDomainCategoryGroup).toList();
    }

    @Override
    public Optional<CategoryGroup> findById(Id id) {
        return jpaRepository.findById(id.value()).map(CategoryGroupMapper::toDomainCategoryGroup);
    }
}
