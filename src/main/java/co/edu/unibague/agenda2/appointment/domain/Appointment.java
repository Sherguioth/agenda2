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

    public Appointment(String id, String description, User user, Session session) {
        this.appointmentId = new Id(id);
        this.appointmentDescription = new AppointmentDescription(description);
        this.appointmentUser = user;
        this.appointmentSession = session;
    }

    public static Appointment create(String id, String description, User user, Session session) {
        return new Appointment(id, description, user, session);
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
