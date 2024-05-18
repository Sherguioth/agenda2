package co.edu.unibague.agenda2.user.domain;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.valueojects.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {

    private final Id id;
    private final UserEmail email;
    private UserPassword password;
    private final UserFirstName firstName;
    private final UserLastName lastName;
    private final UserBirthday birthday;
    private final UserRoles roles;

    public User(String id, String email, String password, String firstName, String lastName,
                String birthday, UserRoles roles) {
        this.id = new Id(id);
        this.email = new UserEmail(email);
        this.password = new UserPassword(password);
        this.firstName = new UserFirstName(firstName);
        this.lastName = new UserLastName(lastName);
        this.birthday = new UserBirthday(birthday);
        this.roles = roles;
    }

    public static User userCreator(String id, String email, String password, String firstName,
                                   String lastName, String birthday) {
        return new User(id, email, password, firstName, lastName, birthday, new UserRoles(new HashSet<>()));
    }

    public UUID getId() {
        return id.value();
    }

    public String getEmail() {
        return email.value();
    }

    public String getPassword() {
        return password.value();
    }

    public String getFirstName() {
        return firstName.value();
    }

    public String getLastName() {
        return lastName.value();
    }

    public LocalDate getBirthday() {
        return birthday.value();
    }

    public Set<Role> getRoles() {
        return roles.value();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void updatePassword(String newPassword) {
        this.password = new UserPassword(newPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id.value() +
                ", email=" + email.value() +
                ", password=" + password.value() +
                ", firstName=" + firstName.value() +
                ", lastName=" + lastName.value() +
                ", birthday=" + birthday.value() +
                '}';
    }
}
