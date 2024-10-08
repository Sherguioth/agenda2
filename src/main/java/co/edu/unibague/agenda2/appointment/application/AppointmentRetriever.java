package co.edu.unibague.agenda2.appointment.application;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.domain.usecases.RetrieveAppointment;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;

public class AppointmentRetriever implements RetrieveAppointment {

    private final AppointmentRepository appointmentRepository;

    public AppointmentRetriever(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(String userId) {
        return appointmentRepository.findAllByUserId(new Id(userId));
    }
}
