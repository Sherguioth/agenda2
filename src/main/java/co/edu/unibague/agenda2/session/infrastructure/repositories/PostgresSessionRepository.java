package co.edu.unibague.agenda2.session.infrastructure.repositories;

import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.session.infrastructure.entities.SessionMapper;
import co.edu.unibague.agenda2.shared.domain.Id;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresSessionRepository implements SessionRepository {

    private final JpaSessionRepository jpaRepository;

    public PostgresSessionRepository(JpaSessionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Session session) {
        var sessionEntity = SessionMapper.toSessionEntity(session);
        var sessionSaved = jpaRepository.save(sessionEntity);
        SessionMapper.toDomainSession(sessionSaved);
    }

    @Override
    public List<Session> findAll() {
        return jpaRepository.findAll().stream().map(SessionMapper::toDomainSession).toList();
    }

    @Override
    public Optional<Session> getSession(Id sessionId) {
        return jpaRepository.findById(sessionId.value()).map(SessionMapper::toDomainSession);
    }
}
