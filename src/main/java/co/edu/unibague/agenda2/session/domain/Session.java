package co.edu.unibague.agenda2.session.domain;

import co.edu.unibague.agenda2.place.domain.Place;
import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.session.domain.valueobjects.SessionDateTime;
import co.edu.unibague.agenda2.session.domain.valueobjects.SessionDescription;
import co.edu.unibague.agenda2.session.domain.valueobjects.SessionUsersLimit;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {

    private final Id sessionId;
    private final SessionDescription sessionDescription;
    private final SessionDateTime sessionDateTime;
    private final SessionUsersLimit sessionUsersLimit;
    private final Schedule sessionSchedule;
    private final Place sessionPlace;

    public Session(String sessionId, String sessionDescription, LocalDateTime sessionDateTime, int sessionUsersLimit, Schedule sessionSchedule,
                   Place place) {
        this.sessionId = new Id(sessionId);
        this.sessionDescription = new SessionDescription(sessionDescription);
        this.sessionDateTime = new SessionDateTime(sessionDateTime);
        this.sessionUsersLimit = new SessionUsersLimit(sessionUsersLimit);
        this.sessionSchedule = sessionSchedule;
        this.sessionPlace = place;
    }

    public static Session create(String sessionId, String sessionDescription, LocalDateTime sessionDateTime,
                                 int sessionUsersLimit, Schedule sessionSchedule, Place place) {
        return new Session(sessionId, sessionDescription, sessionDateTime, sessionUsersLimit, sessionSchedule, place);
    }

    public UUID getId() {
        return sessionId.value();
    }

    public String getSessionDescription() {
        return sessionDescription.vale();
    }

    public LocalDateTime getSessionDateTime() {
        return sessionDateTime.value();
    }

    public int getUsersLimit() {
        return sessionUsersLimit.value();
    }

    public Place getPlace() {
        return sessionPlace;
    }

    public Schedule getSchedule() {
        return sessionSchedule;
    }
}
