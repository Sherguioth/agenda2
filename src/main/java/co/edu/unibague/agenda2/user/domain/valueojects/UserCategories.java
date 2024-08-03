package co.edu.unibague.agenda2.user.domain.valueojects;

import java.util.Set;

public class UserCategories {

    private final Set<UserCategory> categories;

    public UserCategories(Set<UserCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(UserCategory category) {
        categories.add(category);
    }

    public void removeCategory(UserCategory category) {
        categories.remove(category);
    }

    public Set<UserCategory> value() {
        return categories;
    }
}
