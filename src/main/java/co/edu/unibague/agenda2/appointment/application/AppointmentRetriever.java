package co.edu.unibague.agenda2.appointment.application;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.domain.usecases.RetrieveAppointment;

import java.util.List;

public class AppointmentRetriever implements RetrieveAppointment {

    private final AppointmentRepository repository;

    public AppointmentRetriever(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}
