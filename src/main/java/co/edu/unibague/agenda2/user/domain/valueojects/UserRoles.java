package co.edu.unibague.agenda2.user.domain.valueojects;

import co.edu.unibague.agenda2.role.domain.Role;

import java.util.List;

public class UserRoles {

    private final List<Role> roles;

    public UserRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void add(Role role) {
        roles.add(role);
    }

    public List<Role> value() {
        return roles;
    }
}
