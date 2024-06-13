package co.edu.unibague.agenda2.session.application;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.domain.usecases.RetrieveSession;

import java.util.List;

public class SessionRetriever implements RetrieveSession {

    private final SessionRepository sessionRepository;

    public SessionRetriever(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}
