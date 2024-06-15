package co.edu.unibague.agenda2.session.domain;

import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    void save(Session session);

    List<Session> findAll();

    Optional<Session> getSession(Id sessionId);
}
