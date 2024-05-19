package co.edu.unibague.agenda2.schedule.infrastructure.entities;

import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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
    private UserEntity userId;

    public ScheduleEntity() {
    }

    public ScheduleEntity(UUID id, String title, UserEntity userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }
}
