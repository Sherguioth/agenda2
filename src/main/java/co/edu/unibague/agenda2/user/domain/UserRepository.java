package co.edu.unibague.agenda2.user.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Id id);

    List<User> findAll();
}
