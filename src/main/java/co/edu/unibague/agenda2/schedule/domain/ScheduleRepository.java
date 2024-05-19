package co.edu.unibague.agenda2.schedule.domain;

import java.util.List;

public interface ScheduleRepository {

    void save(Schedule schedule);

    List<Schedule> findAll();
}
