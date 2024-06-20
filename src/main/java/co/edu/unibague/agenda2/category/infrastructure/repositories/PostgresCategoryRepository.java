package co.edu.unibague.agenda2.category.infrastructure.repositories;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresCategoryRepository implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    public PostgresCategoryRepository(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public void save(Category category) {
        var categoryEntity = CategoryMapper.toCategoryEntity(category);
        var savedCategoryEntity = jpaCategoryRepository.save(categoryEntity);
        CategoryMapper.toDomainCategory(savedCategoryEntity);
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll().stream().map(CategoryMapper::toDomainCategory).toList();
    }

    @Override
    public Optional<Category> findById(Id id) {
        return jpaCategoryRepository.findById(id.value()).map(CategoryMapper::toDomainCategory);
    }
}
