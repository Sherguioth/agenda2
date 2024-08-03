package co.edu.unibague.agenda2.schedule.domain.valueobjects;

import co.edu.unibague.agenda2.category.domain.SubCategory;

public class ScheduleCategory {

    private final SubCategory subCategory;
    private final boolean isMandatory;

    public ScheduleCategory(SubCategory category, boolean isMandatory) {
        this.subCategory = category;
        this.isMandatory = isMandatory;
    }

    public SubCategory getCategory() {
        return subCategory;
    }

    public boolean isMandatory() {
        return isMandatory;
    }
}
