package co.edu.unibague.agenda2.session.domain.usecases;

import co.edu.unibague.agenda2.session.domain.Session;

import java.util.List;

public interface RetrieveSession {

    List<Session> getAllSessions();
}
