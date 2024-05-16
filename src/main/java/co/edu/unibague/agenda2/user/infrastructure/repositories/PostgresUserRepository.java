package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.infrastructure.entities.RoleMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserMapper;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresUserRepository implements UserRepository {

    private final JpaUserRepository userRepository;
    private final JpaUserRoleRepository userRoleRepository;

    public PostgresUserRepository(JpaUserRepository userRepository, JpaUserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
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
