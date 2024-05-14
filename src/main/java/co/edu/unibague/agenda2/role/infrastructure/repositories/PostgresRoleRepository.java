package co.edu.unibague.agenda2.role.infrastructure.repositories;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.role.infrastructure.entities.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresRoleRepository implements RoleRepository {

    private final JpaRoleRepository repository;

    public PostgresRoleRepository(JpaRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Role role) {
        var rollEntity = RoleMapper.toRoleEntity(role);
        var savedRollEntity = repository.save(rollEntity);
        RoleMapper.toDomainRole(savedRollEntity);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll().stream().map(RoleMapper::toDomainRole).toList();
    }
}
