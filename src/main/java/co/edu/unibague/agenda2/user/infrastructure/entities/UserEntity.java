package co.edu.unibague.agenda2.user.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"User\"")
public class UserEntity {

    @Id
    private UUID id;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "first_name", length = 90)
    private String firstName;

    @Column(name = "last_name", length = 90)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    public UserEntity() { }

    public UserEntity(UUID id, String email, String password, String firstName, String lastName, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
