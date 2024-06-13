package co.edu.unibague.agenda2.schedule.domain;

import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleTitle;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;

import java.util.UUID;

public class Schedule {

    private final Id id;
    private final ScheduleTitle title;
    private final User user;

    public Schedule(String  id, String  title, User user) {
        this.id = new Id(id);
        this.title = new ScheduleTitle(title);
        this.user = user;
    }

    public static Schedule create(String id, String  title, User user) {
        return new Schedule(id, title, user);
    }

    public UUID getId() {
        return id.value();
    }

    public String getTitle() {
        return title.value();
    }

    public User getProfessional() {
        return user;
    }
}
