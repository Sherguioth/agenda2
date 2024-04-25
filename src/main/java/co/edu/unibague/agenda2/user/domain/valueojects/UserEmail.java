package co.edu.unibague.agenda2.user.domain.valueojects;

import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;

public class UserEmail {

    private static final String emailRegexPattern = "^(?=.{1,64}@)[\\\\p{L}0-9_-]+(\\\\.[\\\\p{L}0-9_-]+)*@"
            +"[^-][\\\\p{L}0-9-]+(\\\\.[\\\\p{L}0-9-]+)*(\\\\.[\\\\p{L}]{2,})$";

    private final String email;

    public UserEmail(String email) throws InvalidArgumentException {
        this.validateEmail(email);

        this.email = email;
    }

    public String value() {
        return email;
    }

    public void validateEmail(String email) throws InvalidArgumentException {
        if (!email.matches(emailRegexPattern)){
           throw new InvalidArgumentException(email + " is not a valid email address.");
        }
    }
}
