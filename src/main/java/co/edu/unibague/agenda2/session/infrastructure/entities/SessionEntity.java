package co.edu.unibague.agenda2.session.infrastructure.entities;

import co.edu.unibague.agenda2.place.infrastructure.entities.PlaceEntity;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"Session\"")
public class SessionEntity {

    @Id
    @Column(name = "session_id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @Column(name = "users_limit")
    private int usersLimit;

    @ManyToOne
    @JoinColumn(name = "schedule_id", updatable = false)
    private ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name = "place_id", updatable = false)
    private PlaceEntity place;
}
