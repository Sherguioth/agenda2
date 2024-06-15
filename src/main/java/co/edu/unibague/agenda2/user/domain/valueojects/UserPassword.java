package co.edu.unibague.agenda2.user.domain.valueojects;

public class UserPassword {

    private final String password;

    public UserPassword(String password) {
        //TODO: encrypt password
        this.password = password;
    }

    public String value() {
        return password;
    }
}
