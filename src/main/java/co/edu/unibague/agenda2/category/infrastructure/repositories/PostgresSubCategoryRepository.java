package co.edu.unibague.agenda2.category.infrastructure.repositories;

import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.category.domain.SubCategoryRepository;
import co.edu.unibague.agenda2.category.infrastructure.entities.SubCategoryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresSubCategoryRepository implements SubCategoryRepository {

    private final JpaSubCategoryRepository jpaSubCategoryRepository;

    public PostgresSubCategoryRepository(JpaSubCategoryRepository jpaSubCategoryRepository) {
        this.jpaSubCategoryRepository = jpaSubCategoryRepository;
    }

    @Override
    public void save(SubCategory subCategory) {
        var subCategoryEntity = SubCategoryMapper.toSubCategoryEntity(subCategory);
        var savedSubCategoryEntity = jpaSubCategoryRepository.save(subCategoryEntity);
        SubCategoryMapper.toDomainSubCategory(savedSubCategoryEntity);
    }

    @Override
    public List<SubCategory> findAll() {
        return jpaSubCategoryRepository.findAll().stream().map(SubCategoryMapper::toDomainSubCategory).toList();
    }
}
