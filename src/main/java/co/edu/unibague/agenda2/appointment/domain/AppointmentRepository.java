package co.edu.unibague.agenda2.appointment.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    void save(Appointment appointment);

    List<Appointment> findAll();

    Optional<Appointment> findById(Id id);

    List<Appointment> findAllByUserId(Id userId);

    Appointment update(Appointment appointment);

    void delete(Id id);
}
