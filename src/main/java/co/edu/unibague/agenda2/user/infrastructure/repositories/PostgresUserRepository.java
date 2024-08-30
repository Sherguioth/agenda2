package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryMapper;
import co.edu.unibague.agenda2.category.infrastructure.repositories.JpaCategoryRepository;
import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.infrastructure.entities.RoleMapper;
import co.edu.unibague.agenda2.role.infrastructure.repositories.JpaRoleRepository;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.domain.exceptions.UserNotFoundException;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategories;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategory;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserCategoryEntity;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserMapper;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserRoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class PostgresUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserRoleRepository jpaUserRoleRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaUserCategoryRepository jpaUserCategoryRepository;
    private final JpaCategoryRepository jpaCategoryRepository;

    public PostgresUserRepository(JpaUserRepository jpaUserRepository, JpaUserRoleRepository jpaUserRoleRepository,
                                  JpaRoleRepository jpaRoleRepository, JpaUserCategoryRepository jpaUserCategoryRepository,
                                  JpaCategoryRepository jpaCategoryRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaUserRoleRepository = jpaUserRoleRepository;
        this.jpaRoleRepository = jpaRoleRepository;
        this.jpaUserCategoryRepository = jpaUserCategoryRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public void save(User user) {
        var userEntity = UserMapper.toUserEntity(user);
        var sevedUserEntity = jpaUserRepository.save(userEntity);
        UserMapper.toDomainUser(sevedUserEntity);
    }

    @Override
    public Optional<User> findById(Id id) {
        return jpaUserRepository.findById(id.value()).map(UserMapper::toDomainUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var userEntity = jpaUserRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));
        var user = UserMapper.toDomainUser(userEntity);
        List<UserRoleEntity> userRoleEntities = jpaUserRoleRepository.findAllByUserEntity(userEntity);
        userRoleEntities.forEach(userRoleEntity -> {
            var roleEntity = jpaRoleRepository.findById(userRoleEntity.getRoleEntity().getId()).orElseThrow();
            user.addRole(RoleMapper.toDomainRole(roleEntity));
        });
        return Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream().map(UserMapper::toDomainUser).toList();
    }

    @Override
    public Optional<UserCategories> findUserCategories(User user) {
        var userEntity = jpaUserRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + user.getId() + " not found"));

        List<UserCategoryEntity> userCategoryEntities = jpaUserCategoryRepository.findAllByUserEntity(userEntity);
        Set<UserCategory> userCategories = new HashSet<>();

        userCategoryEntities.forEach(userCategoryEntity -> {
            var category = CategoryMapper.toDomainCategory(
                    jpaCategoryRepository.findById(userCategoryEntity.getCategoryEntity().getId()).orElseThrow()
            );

            userCategories.add(new UserCategory(category, userCategoryEntity.isAnExpert()));
        });

        user.addCategories(userCategories);
        return Optional.of(new UserCategories(userCategories));
    }

    @Override
    public void addRoleToUser(User user, Role role) {
        var userEntity = UserMapper.toUserEntity(user);
        var roleEntity = RoleMapper.toRoleEntity(role);
        var userRoleEntity = new UserRoleEntity(UUID.randomUUID(), userEntity, roleEntity);
        jpaUserRoleRepository.save(userRoleEntity);
    }

    @Override
    public void addCategoryToUser(User user, UserCategory userCategory) {
        var userEntity = UserMapper.toUserEntity(user);
        var categoryEntity = CategoryMapper.toCategoryEntity(userCategory.value());

        var userCategoryEntity = new UserCategoryEntity(
                UUID.randomUUID(),
                userCategory.isAnExpert(),
                userEntity,
                categoryEntity
        );

        jpaUserCategoryRepository.save(userCategoryEntity);
    }

    @Override
    public void removeCategoryFromUser(User user, UserCategory userCategory) {
        var category = userCategory.value();

        var userCategoryEntityOptional = jpaUserCategoryRepository.findByUserEntityAndCategoryEntity(
                UserMapper.toUserEntity(user),
                CategoryMapper.toCategoryEntity(category)
        );

        userCategoryEntityOptional.ifPresent(jpaUserCategoryRepository::delete);
    }
}
