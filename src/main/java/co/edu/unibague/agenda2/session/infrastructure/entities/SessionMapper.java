package co.edu.unibague.agenda2.session.infrastructure.entities;

import co.edu.unibague.agenda2.place.infrastructure.entities.PlaceMapper;
import co.edu.unibague.agenda2.schedule.infrastructure.entities.ScheduleMapper;
import co.edu.unibague.agenda2.session.domain.Session;

public class SessionMapper {

    private SessionMapper() {}

    public static SessionEntity toSessionEntity(Session session) {
        return new SessionEntity(
                session.getId(),
                session.getDescription(),
                session.getDateTime(),
                session.getUsersLimit(),
                ScheduleMapper.toScheduleEntity(session.getSchedule()),
                PlaceMapper.toPlaceEntity(session.getPlace())
        );
    }

    public static Session toDomainSession(SessionEntity sessionEntity) {
        return Session.create(
                sessionEntity.getId().toString(),
                sessionEntity.getDescription(),
                sessionEntity.getDateTime(),
                sessionEntity.getUsersLimit(),
                ScheduleMapper.toDomainSchedule(sessionEntity.getSchedule()),
                PlaceMapper.toDomainPlace(sessionEntity.getPlace())
        );
    }
}
