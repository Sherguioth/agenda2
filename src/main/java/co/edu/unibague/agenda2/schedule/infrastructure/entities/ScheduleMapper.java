package co.edu.unibague.agenda2.schedule.infrastructure.entities;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.user.infrastructure.entities.UserMapper;

public class ScheduleMapper {

    private ScheduleMapper() {
    }

    public static Schedule toDomainSchedule(ScheduleEntity scheduleEntity) {
        return Schedule.create(
                scheduleEntity.getId().toString(),
                scheduleEntity.getTitle(),
                UserMapper.toDomainUser(scheduleEntity.getUserId())
        );
    }

    public static ScheduleEntity toScheduleEntity(Schedule schedule) {
        return new ScheduleEntity(
                schedule.getId(),
                schedule.getTitle(),
                UserMapper.toUserEntity(schedule.getProfessional())
        );
    }
}
