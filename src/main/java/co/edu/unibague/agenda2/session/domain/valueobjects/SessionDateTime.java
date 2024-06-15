package co.edu.unibague.agenda2.session.domain.valueobjects;

import java.time.LocalDateTime;

public class SessionDateTime {

    private final LocalDateTime localDateTime;

    public SessionDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime value() {
        return this.localDateTime;
    }
}
