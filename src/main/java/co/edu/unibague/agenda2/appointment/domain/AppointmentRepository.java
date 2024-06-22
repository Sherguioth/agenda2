package co.edu.unibague.agenda2.appointment.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;

public interface AppointmentRepository {

    void save(Appointment appointment);

    List<Appointment> findAll();

    Appointment update(Appointment appointment);

    void delete(Id id);
}
