package co.edu.unibague.agenda2.schedule.domain.valueobjects;

public class ScheduleTitle {
    private final String title;

    public ScheduleTitle(String title) {
        this.title = title;
    }

    public String value() {
        return title;
    }
}
