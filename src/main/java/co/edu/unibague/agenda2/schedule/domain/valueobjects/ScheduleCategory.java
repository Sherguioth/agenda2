package co.edu.unibague.agenda2.schedule.domain.valueobjects;

import co.edu.unibague.agenda2.category.domain.Category;

public class ScheduleCategory {

    private final Category category;
    private final boolean isMandatory;

    public ScheduleCategory(Category category, boolean isMandatory) {
        this.category = category;
        this.isMandatory = isMandatory;
    }

    public Category value() {
        return category;
    }

    public boolean isMandatory() {
        return isMandatory;
    }
}
