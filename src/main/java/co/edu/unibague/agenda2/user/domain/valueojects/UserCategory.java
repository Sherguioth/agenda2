package co.edu.unibague.agenda2.user.domain.valueojects;

import co.edu.unibague.agenda2.category.domain.SubCategory;

import java.util.Objects;

public class UserCategory {

    private final SubCategory subCategory;
    private final boolean isAnExpert;

    public UserCategory(SubCategory category, boolean isAnExpert) {
        this.subCategory = category;
        this.isAnExpert = isAnExpert;
    }

    public SubCategory getCategory() {
        return subCategory;
    }

    public boolean isAnExpert() {
        return isAnExpert;
    }

}
