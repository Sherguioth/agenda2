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
    private ScheduleCategories categories;

    public Schedule(String  id, String  title, User user, ScheduleCategories categories) {
        this.id = new Id(id);
        this.title = new ScheduleTitle(title);
        this.user = user;
        this.categories = categories;
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
        return categories.value();
    }

    public void addCategory(ScheduleCategory category) {
        categories.addCategory(category);
    }

    public void addCategories(Set<ScheduleCategory> categories) {
        this.categories = new ScheduleCategories(categories);
    }

    public Set<ScheduleCategory> getMandatoryCategories(){
        var result = new HashSet<ScheduleCategory>();

        categories.value().stream()
                .filter(ScheduleCategory::isMandatory)
                .forEach(result::add);

        return result;
    }

    public void removeCategory(ScheduleCategory category) {
        categories.removeCategory(category);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id.value() +
                ", title=" + title.value() +
                ", user=" + user +
                ", categories=" + categories.toString() +
                '}';
    }
}
