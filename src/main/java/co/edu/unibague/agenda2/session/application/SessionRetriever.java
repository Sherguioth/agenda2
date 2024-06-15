package co.edu.unibague.agenda2.session.application;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.domain.usecases.RetrieveSession;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public class SessionRetriever implements RetrieveSession {

    private final SessionRepository sessionRepository;

    public SessionRetriever(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Session> getSession(String sessionId) {
        return sessionRepository.getSession(new Id(sessionId));
    }
}
