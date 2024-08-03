package co.edu.unibague.agenda2.user.infrastructure.entities;

import co.edu.unibague.agenda2.category.infrastructure.entities.SubCategoryEntity;
import jakarta.persistence.*;
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
@Table(name = "\"User_Category\"")
public class UserCategoryEntity {

    @Id
    @Column(name = "user_category_id")
    private UUID id;

    @Column(name = "is_an_expert")
    private boolean isAnExpert;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "category_id", updatable = false)
    private SubCategoryEntity categoryEntity;

}
