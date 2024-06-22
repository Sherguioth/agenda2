package co.edu.unibague.agenda2.schedule.domain.valueobjects;

import java.util.Set;

public class ScheduleCategories {

    private final Set<ScheduleCategory> categories;

    public ScheduleCategories(Set<ScheduleCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(ScheduleCategory category) {
        categories.add(category);
    }

    public Set<ScheduleCategory> value() {
        return categories;
    }
}
