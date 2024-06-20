package co.edu.unibague.agenda2.appointment.infrastructure.api;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.usecases.CreateAppointment;
import co.edu.unibague.agenda2.appointment.domain.usecases.RetrieveAppointment;
import co.edu.unibague.agenda2.session.domain.usecases.RetrieveSession;
import co.edu.unibague.agenda2.user.domain.usecases.RetrieveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final CreateAppointment appointmentCreator;
    private final RetrieveAppointment appointmentRetriever;
    private final RetrieveUser userRetriever;
    private final RetrieveSession sessionRetriever;


    public AppointmentController(CreateAppointment appointmentCreator, RetrieveAppointment appointmentRetriever,
                                 RetrieveUser userRetriever, RetrieveSession sessionRetriever) {
        this.appointmentCreator = appointmentCreator;
        this.appointmentRetriever = appointmentRetriever;
        this.userRetriever = userRetriever;
        this.sessionRetriever = sessionRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentInput appointmentInput) {
        var user = userRetriever.getUser(appointmentInput.userId()).orElseThrow();
        var session = sessionRetriever.getSession(appointmentInput.sessionId()).orElseThrow();
        appointmentCreator.createAppointment(Appointment.create(appointmentInput.id(), appointmentInput.description(), user, session));
        log.info("Appointment created: {}", appointmentInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAppointments() {
        List<Appointment> appointmentList = appointmentRetriever.getAllAppointments();
        List<AppointmentResponse> appointmentResponses = appointmentList.stream().map(
                appointment -> new AppointmentResponse(
                        appointment.getId(),
                        appointment.getDescription(),
                        appointment.gettUser().getId(),
                        appointment.getSession().getId()
                )
        ).toList();

        return ResponseEntity.ok().body(appointmentResponses);
    }
}
