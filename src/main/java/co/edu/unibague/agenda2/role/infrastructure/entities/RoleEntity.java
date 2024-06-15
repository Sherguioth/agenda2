package co.edu.unibague.agenda2.role.infrastructure.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"Role\"")
public class RoleEntity {

    @Id
    @Column(name = "role_id")
    private UUID id;

    @Column(name = "name", length = 25, unique = true, updatable = false)
    private String name;
}
