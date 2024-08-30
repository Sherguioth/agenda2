package co.edu.unibague.agenda2.category.domain.valueobjects;

public class CategoryGroupName {

    private final String name;

    public CategoryGroupName(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }
}
