package co.edu.unibague.agenda2.schedule.infrastructure.entities;

import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
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
@Table(name = "\"Schedule\"")
public class ScheduleEntity {

    @Id
    @Column(name = "schedule_id")
    private UUID id;

    @Column(name = "title", length = 120)
    private String title;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private UserEntity userEntity;
}
