package co.edu.unibague.agenda2.appointment.application;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.domain.usecases.CreateAppointment;

public class AppointmentCreator implements CreateAppointment {

    private final AppointmentRepository repository;

    public AppointmentCreator(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAppointment(Appointment appointment) {
        repository.save(appointment);
    }
}
