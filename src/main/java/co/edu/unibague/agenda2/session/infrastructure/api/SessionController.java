package co.edu.unibague.agenda2.session.infrastructure.api;

import co.edu.unibague.agenda2.place.domain.usecases.RetrievePlace;
import co.edu.unibague.agenda2.schedule.domain.usecases.RetrieveSchedule;
import co.edu.unibague.agenda2.session.domain.Session;
import co.edu.unibague.agenda2.session.domain.usecases.CreateSession;
import co.edu.unibague.agenda2.session.domain.usecases.RetrieveSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final CreateSession sessionCreator;
    private final RetrieveSession sessionRetriever;
    private final RetrieveSchedule scheduleRetriever;
    private final RetrievePlace placeRetriever;

    public SessionController(CreateSession sessionCreator, RetrieveSession sessionRetriever,
                             RetrieveSchedule scheduleRetriever, RetrievePlace placeRetriever) {
        this.sessionCreator = sessionCreator;
        this.sessionRetriever = sessionRetriever;
        this.scheduleRetriever = scheduleRetriever;
        this.placeRetriever = placeRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createSession(@RequestBody SessionInput sessionInput) {
        var schedule = scheduleRetriever.getSchedule(sessionInput.scheduleId()).orElseThrow();
        var place = placeRetriever.getPlace(sessionInput.placeId()).orElseThrow();
        sessionCreator.createSession(Session.create(sessionInput.id(), sessionInput.description(),
                LocalDateTime.parse(sessionInput.dateTime()), sessionInput.usersLimit(), schedule, place));
        log.info("Session created: {}", sessionInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions() {
        List<Session> sessionList = sessionRetriever.getAllSessions();
        List<SessionResponse> sessionResponses = sessionList.stream().map(
                session -> new SessionResponse(
                        session.getId(),
                        session.getSessionDescription(),
                        session.getSessionDateTime(),
                        session.getUsersLimit(),
                        session.getSchedule().getId(),
                        session.getSchedule().getTitle(),
                        session.getPlace().getId(),
                        session.getPlace().getPlaceName()
                )
        ).toList();
        return ResponseEntity.ok().body(sessionResponses);
    }
}
