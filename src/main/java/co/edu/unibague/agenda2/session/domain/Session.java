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

    public Session(String id, String description, LocalDateTime dateTime, int usersLimit, Schedule schedule, Place place) {
        this.sessionId = new Id(id);
        this.sessionDescription = new SessionDescription(description);
        this.sessionDateTime = new SessionDateTime(dateTime);
        this.sessionUsersLimit = new SessionUsersLimit(usersLimit);
        this.sessionSchedule = schedule;
        this.sessionPlace = place;
    }

    public static Session create(String id, String description, LocalDateTime dateTime, int usersLimit,
                                 Schedule schedule, Place place) {
        return new Session(id, description, dateTime, usersLimit, schedule, place);
    }

    public UUID getId() {
        return sessionId.value();
    }

    public String getDescription() {
        return sessionDescription.vale();
    }

    public LocalDateTime getDateTime() {
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
