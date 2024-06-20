package co.edu.unibague.agenda2.category.domain.valueobjects;

public class CategoryName {

    private final String name;

    public CategoryName(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }
}
