package co.edu.unibague.agenda2.category.infrastructure.entities;

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
@Table(name = "\"Category\"")
public class CategoryEntity {

    @Id
    @Column(name = "category_id")
    private UUID id;

    @Column(name = "name", unique = true, length = 120)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_group_id", updatable = false)
    private CategoryGroupEntity categoryGroupEntity;
}
