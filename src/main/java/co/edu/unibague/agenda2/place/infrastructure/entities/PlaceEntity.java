package co.edu.unibague.agenda2.place.infrastructure.entities;

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
@Table(name = "\"Place\"")
public class PlaceEntity {

    @Id
    @Column(name = "place_id")
    private UUID id;

    @Column(name = "name", length = 120)
    private String name;

    @Column(name = "address")
    private String address;
}
