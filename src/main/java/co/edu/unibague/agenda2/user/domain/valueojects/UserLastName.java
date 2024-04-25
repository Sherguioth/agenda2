package co.edu.unibague.agenda2.user.domain.valueojects;

public class UserLastName {

    private final String lastName;

    public UserLastName(String lastName) {
        this.lastName = lastName;
    }

    public String value() {
        return this.lastName;
    }
}
