package co.edu.unibague.agenda2.role.application;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.role.domain.usecases.CreateRole;

public class RoleCreator implements CreateRole {

    private final RoleRepository roleRepository;

    public RoleCreator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }
}
