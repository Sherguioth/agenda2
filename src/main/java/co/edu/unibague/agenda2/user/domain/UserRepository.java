package co.edu.unibague.agenda2.user.domain;

import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user) throws InvalidArgumentException;

    Optional<User> findById(Id id);

    List<User> findAll();
}
