package co.edu.unibague.agenda2.session.domain.valueobjects;

public class SessionUsersLimit {

    private final int limit;

    public SessionUsersLimit(int limit) {
        this.limit = limit;
    }

    public int value() {
        return this.limit;
    }
}
