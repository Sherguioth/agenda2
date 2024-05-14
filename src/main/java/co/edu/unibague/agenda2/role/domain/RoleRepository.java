package co.edu.unibague.agenda2.role.domain;

import java.util.List;

public interface RoleRepository {

    void save(Role role);

    List<Role> findAll();
}
