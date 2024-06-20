package co.edu.unibague.agenda2.role.application;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.role.domain.usecases.CreateRole;

public class RoleCreator implements CreateRole {

    private final RoleRepository repository;

    public RoleCreator(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createRole(Role role) {
        repository.save(role);
    }
}
