package co.edu.unibague.agenda2.user.infrastructure.entities;

import co.edu.unibague.agenda2.role.infrastructure.entities.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"User_Role\"")
public class UserRoleEntity {

    @Id
    @Column(name = "user_role_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false)
    private RoleEntity roleId;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UUID id, UserEntity userId, RoleEntity roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
