package co.edu.unibague.agenda2.appointment.domain.valueobjects;

public class AppointmentDescription {

    private final String appointmentDescription;

    public AppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String value() {
        return appointmentDescription;
    }
}
