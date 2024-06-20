package co.edu.unibague.agenda2.appointment.application;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.appointment.domain.usecases.CreateAppointment;

public class AppointmentCreator implements CreateAppointment {

    private final AppointmentRepository appointmentRepository;

    public AppointmentCreator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}
