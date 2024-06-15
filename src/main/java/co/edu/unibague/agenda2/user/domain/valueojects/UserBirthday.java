package co.edu.unibague.agenda2.user.domain.valueojects;

import java.time.LocalDate;

public class UserBirthday {

    private final LocalDate birthDate;

    public UserBirthday(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate);
    }

    public LocalDate value() {
        return birthDate;
    }
}
