package co.edu.unibague.agenda2.user.domain.valueojects;

import co.edu.unibague.agenda2.category.domain.Category;

public class UserCategory {

    private final Category category;
    private final boolean isAnExpert;

    public UserCategory(Category category, boolean isAnExpert) {
        this.category = category;
        this.isAnExpert = isAnExpert;
    }

    public Category value() {
        return category;
    }

    public boolean isAnExpert() {
        return isAnExpert;
    }

}
