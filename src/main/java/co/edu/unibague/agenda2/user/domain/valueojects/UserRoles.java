package co.edu.unibague.agenda2.user.domain.valueojects;

import co.edu.unibague.agenda2.role.domain.Role;

import java.util.Set;

public class UserRoles {

    private final Set<Role> roles;

    public UserRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void add(Role role) {
        roles.add(role);
    }

    public Set<Role> value() {
        return roles;
    }
}
