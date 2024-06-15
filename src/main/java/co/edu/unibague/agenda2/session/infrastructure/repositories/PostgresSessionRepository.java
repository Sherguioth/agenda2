package co.edu.unibague.agenda2.session.infrastructure.repositories;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.infrastructure.entities.SessionMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresSessionRepository implements SessionRepository {

    private final JpaSessionRepository repository;

    public PostgresSessionRepository(JpaSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Session session) {
        var sessionEntity = SessionMapper.toSessionEntity(session);
        var sessionSaved = repository.save(sessionEntity);
        SessionMapper.toDomainSession(sessionSaved);
    }

    @Override
    public List<Session> findAll() {
        return repository.findAll().stream().map(SessionMapper::toDomainSession).toList();
    }

    @Override
    public Optional<Session> getSession(Id sessionId) {
        return repository.findById(sessionId.value()).map(SessionMapper::toDomainSession);
    }
}
