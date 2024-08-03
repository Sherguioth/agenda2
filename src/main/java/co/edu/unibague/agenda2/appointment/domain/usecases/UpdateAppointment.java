package co.edu.unibague.agenda2.appointment.domain.usecases;

import co.edu.unibague.agenda2.appointment.domain.Appointment;

import java.util.Optional;

public interface UpdateAppointment {

    Optional<Appointment> updateAppointment(Appointment appointment);

}
