package co.edu.unibague.agenda2.session.application;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.domain.usecases.CreateSession;

public class SessionCreator implements CreateSession {

    private final SessionRepository repository;


    public SessionCreator(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createSession(Session session) {
        repository.save(session);
    }
}
