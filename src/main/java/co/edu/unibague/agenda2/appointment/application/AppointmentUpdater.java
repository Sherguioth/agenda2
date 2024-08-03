package co.edu.unibague.agenda2.appointment.application;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.domain.usecases.UpdateAppointment;

import java.util.Optional;

public class AppointmentUpdater implements UpdateAppointment {

    private final AppointmentRepository repository;

    public AppointmentUpdater(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Appointment> updateAppointment(Appointment appointment) {
        return Optional.of(repository.update(appointment));
    }
}
