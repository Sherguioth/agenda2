package co.edu.unibague.agenda2.session.domain.usecases;

import co.edu.unibague.agenda2.session.domain.Session;

import java.util.List;
import java.util.Optional;

public interface RetrieveSession {

    List<Session> getAllSessions();

    Optional<Session> getSession(String sessionId);
}
