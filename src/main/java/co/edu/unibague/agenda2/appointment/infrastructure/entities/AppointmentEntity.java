package co.edu.unibague.agenda2.appointment.infrastructure.entities;


import co.edu.unibague.agenda2.session.infrastructure.entities.SessionEntity;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"Appointment\"")
public class AppointmentEntity {

    @Id
    @Column(name = "appointment_id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "session_id", updatable = false)
    private SessionEntity sessionId;
}
