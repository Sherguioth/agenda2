package co.edu.unibague.agenda2.appointment.infrastructure.entities;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.session.infrastructure.entities.SessionMapper;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserMapper;

public class AppointmentMapper {

    private AppointmentMapper() {
    }

    public static AppointmentEntity toAppointmentEntity(Appointment appointment) {
        return new AppointmentEntity(
                appointment.getId(),
                appointment.getDescription(),
                UserMapper.toUserEntity(appointment.gettUser()),
                SessionMapper.toSessionEntity(appointment.getSession())
        );
    }

    public static Appointment toDomainAppointment(AppointmentEntity appointmentEntity) {
        return Appointment.create(
                appointmentEntity.getId().toString(),
                appointmentEntity.getDescription(),
                UserMapper.toDomainUser(appointmentEntity.getUserEntity()),
                SessionMapper.toDomainSession(appointmentEntity.getSessionEntity())
        );
    }
}
