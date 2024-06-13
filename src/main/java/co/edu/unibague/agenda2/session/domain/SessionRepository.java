package co.edu.unibague.agenda2.session.domain;

import java.util.List;

public interface SessionRepository {

    void save(Session session);

    List<Session> findAll();
}
