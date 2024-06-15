package co.edu.unibague.agenda2.session.application;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.domain.usecases.CreateSession;

public class SessionCreator implements CreateSession {

    private final SessionRepository sessionRepository;


    public SessionCreator(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void createSession(Session session) {
        sessionRepository.save(session);
    }
}
