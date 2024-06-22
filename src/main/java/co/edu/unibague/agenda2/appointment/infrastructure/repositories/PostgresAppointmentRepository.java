package co.edu.unibague.agenda2.appointment.infrastructure.repositories;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.infrastructure.entities.AppointmentMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresAppointmentRepository implements AppointmentRepository {

    private final JpaAppointmentRepository jpaRepository;

    public PostgresAppointmentRepository(JpaAppointmentRepository repository) {
        this.jpaRepository = repository;
    }

    @Override
    public void save(Appointment appointment) {
        var appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        var appointmentSaved = jpaRepository.save(appointmentEntity);
        AppointmentMapper.toDomainAppointment(appointmentSaved);
    }

    @Override
    public List<Appointment> findAll() {
        return jpaRepository.findAll().stream().map(AppointmentMapper::toDomainAppointment).toList();
    }

    @Override
    public Appointment update(Appointment appointment) {
        var appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        var appointmentSaved = jpaRepository.save(appointmentEntity);
        return AppointmentMapper.toDomainAppointment(appointmentSaved);
    }

    @Override
    public void delete(Id id) {
        jpaRepository.deleteById(id.value());
    }
}
