package co.edu.unibague.agenda2.role.application;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.role.domain.usecases.RetrieveRole;

import java.util.List;

public class RoleRetriever implements RetrieveRole {

    private final RoleRepository roleRepository;

    public RoleRetriever(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
