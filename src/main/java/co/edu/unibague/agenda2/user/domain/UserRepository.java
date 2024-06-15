package co.edu.unibague.agenda2.user.domain;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Id id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void addRoleToUser(User user, Role role);
}
