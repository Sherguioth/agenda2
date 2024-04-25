package co.edu.unibague.agenda2.user.infrastructure.repositories;

import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresUserRepository implements UserRepository {

    private final JpaUserRepository repository;

    public PostgresUserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) throws InvalidArgumentException {
        var userEntity = UserMapper.toUserEntity(user);
        var sevedUserEntity = repository.save(userEntity);
        return UserMapper.toDomainUser(sevedUserEntity);
    }

    @SneakyThrows
    @Override
    public Optional<User> findById(Id id) {
        return repository.findById(id.value()).map(UserMapper::toDomainUser);
    }

    @SneakyThrows
    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(UserMapper::toDomainUser).toList();
    }
}
