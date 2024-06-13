package co.edu.unibague.agenda2.appointment.domain;

import co.edu.unibague.agenda2.appointment.domain.valueobjects.AppointmentDescription;
import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;

import java.util.UUID;

public class Appointment {

    private final Id appointmentId;
    private final AppointmentDescription appointmentDescription;
    private final User appointmentUser;
    private final Session appointmentSession;

    public Appointment(String appointmentId, String appointmentDescription, User appointmentUser, Session appointmentSession) {
        this.appointmentId = new Id(appointmentId);
        this.appointmentDescription = new AppointmentDescription(appointmentDescription);
        this.appointmentUser = appointmentUser;
        this.appointmentSession = appointmentSession;
    }

    public static Appointment create(String appointmentId, String appointmentDescription, User appointmentUser,
                                     Session appointmentSession) {
        return new Appointment(appointmentId, appointmentDescription, appointmentUser, appointmentSession);
    }

    public UUID getId() {
        return appointmentId.value();
    }

    public String getDescription() {
        return appointmentDescription.value();
    }

    public User gettUser() {
        return appointmentUser;
    }

    public Session getSession() {
        return appointmentSession;
    }
}
