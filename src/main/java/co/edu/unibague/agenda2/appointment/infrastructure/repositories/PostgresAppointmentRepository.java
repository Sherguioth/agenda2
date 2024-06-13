package co.edu.unibague.agenda2.appointment.infrastructure.repositories;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.infrastructure.entities.AppointmentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresAppointmentRepository implements AppointmentRepository {

    private final JpaAppointmentRepository repository;

    public PostgresAppointmentRepository(JpaAppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Appointment appointment) {
        var appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        var appointmentSaved = repository.save(appointmentEntity);
        AppointmentMapper.toDomainAppointment(appointmentSaved);
    }

    @Override
    public List<Appointment> findAll() {
        return repository.findAll().stream().map(AppointmentMapper::toDomainAppointment).toList();
    }
}
