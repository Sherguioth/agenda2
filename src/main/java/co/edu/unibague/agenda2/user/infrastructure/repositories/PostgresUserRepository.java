package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.infrastructure.entities.RoleMapper;
import co.edu.unibague.agenda2.role.infrastructure.repositories.JpaRoleRepository;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.domain.exceptions.UserNotFoundException;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserMapper;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserRoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class PostgresUserRepository implements UserRepository {

    private final JpaUserRepository userRepository;
    private final JpaUserRoleRepository userRoleRepository;
    private final JpaRoleRepository roleRepository;

    public PostgresUserRepository(JpaUserRepository userRepository, JpaUserRoleRepository userRoleRepository, JpaRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user) {
        var userEntity = UserMapper.toUserEntity(user);
        var sevedUserEntity = userRepository.save(userEntity);
        UserMapper.toDomainUser(sevedUserEntity);
    }

    @Override
    public Optional<User> findById(Id id) {
        return userRepository.findById(id.value()).map(UserMapper::toDomainUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));
        var user = UserMapper.toDomainUser(userEntity);
        List<UserRoleEntity> userRoleEntities = userRoleRepository.findAllByUserId(userEntity);
        userRoleEntities.forEach(userRoleEntity -> {
            var roleEntity = roleRepository.findById(userRoleEntity.getRoleId().getId()).orElseThrow();
            user.addRole(RoleMapper.toDomainRole(roleEntity));
        });
        return Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDomainUser).toList();
    }

    @Override
    public void addRoleToUser(User user, Role role) {
        var userEntity = UserMapper.toUserEntity(user);
        var roleEntity = RoleMapper.toRoleEntity(role);
        var userRoleEntity = new UserRoleEntity(UUID.randomUUID(), userEntity, roleEntity);
        userRoleRepository.save(userRoleEntity);
    }
}
