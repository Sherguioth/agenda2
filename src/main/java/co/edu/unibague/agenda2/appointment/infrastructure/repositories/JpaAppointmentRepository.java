package co.edu.unibague.agenda2.appointment.infrastructure.repositories;

import co.edu.unibague.agenda2.appointment.infrastructure.entities.AppointmentEntity;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findAllByUserEntity(UserEntity userEntity);
}
