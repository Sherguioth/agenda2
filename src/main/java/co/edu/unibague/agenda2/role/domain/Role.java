package co.edu.unibague.agenda2.role.domain;

import co.edu.unibague.agenda2.role.domain.valueobjects.RoleName;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.UUID;

public class Role {

    private final Id id;
    private final RoleName roleName;

    public Role(String id, String roleName) {
        this.id = new Id(id);
        this.roleName = new RoleName(roleName);
    }

    public static Role createRole(String id, String roleName) {
        return new Role(id, roleName);
    }

    public UUID getId() {
        return id.value();
    }

    public String getRoleName() {
        return roleName.value();
    }

    @Override
    public String toString() {
        return "{roleName=" + getRoleName() + '}';
    }
}
