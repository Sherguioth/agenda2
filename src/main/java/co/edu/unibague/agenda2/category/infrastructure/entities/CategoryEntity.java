package co.edu.unibague.agenda2.category.infrastructure.entities;

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
@Table(name = "\"Category\"")
public class CategoryEntity {

    @Id
    @Column(name = "category_id")
    private UUID id;

    @Column(name = "name", unique = true, length = 120)
    private String name;
}
