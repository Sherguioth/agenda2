package co.edu.unibague.agenda2.role.domain.valueobjects;

public class RoleName {

    private final String roleName;

    public RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String value() {
        return this.roleName;
    }
}
