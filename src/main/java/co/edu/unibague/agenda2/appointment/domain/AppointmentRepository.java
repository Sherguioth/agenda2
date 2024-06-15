package co.edu.unibague.agenda2.appointment.domain;

import java.util.List;

public interface AppointmentRepository {

    void save(Appointment appointment);

    List<Appointment> findAll();
}
