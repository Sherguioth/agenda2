package co.edu.unibague.agenda2.schedule.domain;

import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategories;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleTitle;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Schedule {

    private final Id id;
    private final ScheduleTitle title;
    private final User user;
    private final ScheduleCategories scheduleCategories;

    public Schedule(String  id, String  title, User user, ScheduleCategories categories) {
        this.id = new Id(id);
        this.title = new ScheduleTitle(title);
        this.user = user;
        this.scheduleCategories = categories;
    }

    public static Schedule create(String id, String  title, User user) {
        return new Schedule(id, title, user, new ScheduleCategories(new HashSet<>()));
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

    public Set<ScheduleCategory> getCategories() {
        return scheduleCategories.value();
    }

    public void addCategory(ScheduleCategory category) {
        scheduleCategories.addCategory(category);
    }
}
