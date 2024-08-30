package co.edu.unibague.agenda2.category.infrastructure.repositories;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresCategoryRepository implements CategoryRepository {

    private final JpaCategoryRepository jpaRepository;

    public PostgresCategoryRepository(JpaCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Category category) {
        var categoryEntity = CategoryMapper.toCategoryEntity(category);
        var savedCategoryEntity = jpaRepository.save(categoryEntity);
        CategoryMapper.toDomainCategory(savedCategoryEntity);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll().stream().map(CategoryMapper::toDomainCategory).toList();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return jpaRepository.findByName(name).map(CategoryMapper::toDomainCategory);
    }
}
