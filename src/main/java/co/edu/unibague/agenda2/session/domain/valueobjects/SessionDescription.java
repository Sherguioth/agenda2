package co.edu.unibague.agenda2.session.domain.valueobjects;

public class SessionDescription {

    private final String description;

    public SessionDescription(String description) {
        this.description = description;
    }

    public String vale() {
        return this.description;
    }
}
