package co.edu.unibague.agenda2.appointment.application;

import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.domain.usecases.DeleteAppointment;
import co.edu.unibague.agenda2.shared.domain.Id;

public class AppointmentDeleter implements DeleteAppointment {

    private final AppointmentRepository repository;

    public AppointmentDeleter(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAppointment(String appointmentId) {
        repository.delete(new Id(appointmentId));
    }
}
