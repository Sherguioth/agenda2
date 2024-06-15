package co.edu.unibague.agenda2.user.domain.valueojects;

public class UserFirstName {

    private final String firstName;

    public UserFirstName(String value) {
        this.firstName = value;
    }

    public String value() {
        return this.firstName;
    }
}
