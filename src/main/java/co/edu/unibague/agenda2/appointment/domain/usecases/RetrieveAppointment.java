package co.edu.unibague.agenda2.appointment.domain.usecases;

import co.edu.unibague.agenda2.appointment.domain.Appointment;

import java.util.List;

public interface RetrieveAppointment {

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsByUserId(String userId);
}
