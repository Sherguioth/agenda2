package co.edu.unibague.agenda2.appointment.infrastructure.repositories;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.infrastructure.entities.AppointmentMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.infrastructure.repositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresAppointmentRepository implements AppointmentRepository {

    private final JpaAppointmentRepository jpaAppointmentRepository;
    private final JpaUserRepository jpaUserRepository;

    public PostgresAppointmentRepository(JpaAppointmentRepository repository, JpaUserRepository jpaUserRepository) {
        this.jpaAppointmentRepository = repository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void save(Appointment appointment) {
        var appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        var appointmentSaved = jpaAppointmentRepository.save(appointmentEntity);
        AppointmentMapper.toDomainAppointment(appointmentSaved);
    }

    @Override
    public List<Appointment> findAll() {
        return jpaAppointmentRepository.findAll().stream().map(AppointmentMapper::toDomainAppointment).toList();
    }

    @Override
    public Optional<Appointment> findById(Id id) {
        return jpaAppointmentRepository.findById(id.value()).map(AppointmentMapper::toDomainAppointment);
    }

    @Override
    public List<Appointment> findAllByUserId(Id userId) {
        var userEntity = jpaUserRepository.findById(userId.value())
                .orElseThrow(() -> new RuntimeException("User with " + userId.value() + " not found"));
        return jpaAppointmentRepository.findAllByUserEntity(userEntity).stream()
                .map(AppointmentMapper::toDomainAppointment).toList();
    }

    @Override
    public Appointment update(Appointment appointment) {
        var appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        var appointmentSaved = jpaAppointmentRepository.save(appointmentEntity);
        return AppointmentMapper.toDomainAppointment(appointmentSaved);
    }

    @Override
    public void delete(Id id) {
        jpaAppointmentRepository.deleteById(id.value());
    }
}
