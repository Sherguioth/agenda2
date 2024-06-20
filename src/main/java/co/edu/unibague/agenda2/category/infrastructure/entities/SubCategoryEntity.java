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
@Table(name = "\"Sub_Category\"")
public class SubCategoryEntity {

    @Id
    @Column(name = "sub_category_id")
    private UUID id;

    @Column(name = "name", unique = true, length = 120)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", updatable = false)
    private CategoryEntity categoryEntity;
}
