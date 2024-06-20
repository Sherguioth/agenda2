package co.edu.unibague.agenda2.role.infrastructure.repositories;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.role.infrastructure.entities.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresRoleRepository implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public PostgresRoleRepository(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public void save(Role role) {
        var rollEntity = RoleMapper.toRoleEntity(role);
        var savedRollEntity = jpaRoleRepository.save(rollEntity);
        RoleMapper.toDomainRole(savedRollEntity);
    }

    @Override
    public List<Role> findAll() {
        return jpaRoleRepository.findAll().stream().map(RoleMapper::toDomainRole).toList();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return Optional.of(RoleMapper.toDomainRole(jpaRoleRepository.findByName(name)));
    }
}
