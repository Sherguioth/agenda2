package co.edu.unibague.agenda2.role.domain;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    void save(Role role);

    List<Role> findAll();

    Optional<Role> findByName(String name);
}
