package co.edu.unibague.agenda2.session.application;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.domain.usecases.RetrieveSession;
import co.edu.unibague.agenda2.shared.domain.Id;

import java.util.List;
import java.util.Optional;

public class SessionRetriever implements RetrieveSession {

    private final SessionRepository repository;

    public SessionRetriever(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Session> getAllSessions() {
        return repository.findAll();
    }

    @Override
    public Optional<Session> getSession(String sessionId) {
        return repository.getSession(new Id(sessionId));
    }
}
