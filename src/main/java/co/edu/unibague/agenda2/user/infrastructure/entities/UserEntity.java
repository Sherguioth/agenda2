package co.edu.unibague.agenda2.user.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"User\"")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "first_name", length = 90)
    private String firstName;

    @Column(name = "last_name", length = 90)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

}
