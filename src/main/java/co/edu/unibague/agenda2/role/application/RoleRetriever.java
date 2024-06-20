package co.edu.unibague.agenda2.role.application;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.role.domain.usecases.RetrieveRole;

import java.util.List;

public class RoleRetriever implements RetrieveRole {

    private final RoleRepository repository;

    public RoleRetriever(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}
