package co.edu.unibague.agenda2.appointment.infrastructure.api;

import co.edu.unibague.agenda2.appointment.domain.Appointment;
import co.edu.unibague.agenda2.appointment.domain.usecases.CreateAppointment;
import co.edu.unibague.agenda2.appointment.domain.usecases.DeleteAppointment;
import co.edu.unibague.agenda2.appointment.domain.usecases.RetrieveAppointment;
import co.edu.unibague.agenda2.appointment.domain.usecases.UpdateAppointment;
import co.edu.unibague.agenda2.session.domain.usecases.RetrieveSession;
import co.edu.unibague.agenda2.user.domain.usecases.RetrieveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final CreateAppointment appointmentCreator;
    private final RetrieveAppointment appointmentRetriever;
    private final UpdateAppointment appointmentUpdater;
    private final DeleteAppointment appointmentDeleter;
    private final RetrieveUser userRetriever;
    private final RetrieveSession sessionRetriever;


    public AppointmentController(CreateAppointment appointmentCreator, RetrieveAppointment appointmentRetriever,
                                 UpdateAppointment appointmentUpdater, DeleteAppointment appointmentDeleter,
                                 RetrieveUser userRetriever, RetrieveSession sessionRetriever) {
        this.appointmentCreator = appointmentCreator;
        this.appointmentRetriever = appointmentRetriever;
        this.appointmentUpdater = appointmentUpdater;
        this.appointmentDeleter = appointmentDeleter;
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
        log.info("Appointments retrieved: {}", appointmentResponses.size());
        return ResponseEntity.ok().body(appointmentResponses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentSummary>> getAppointmentsByUserId(@PathVariable("userId") String userId) {
        List<Appointment> appointmentList = appointmentRetriever.getAppointmentsByUserId(userId);
        List<AppointmentSummary> appointmentSummaries = new ArrayList<>();
        appointmentList.forEach(appointment -> {
            appointmentSummaries.add(new AppointmentSummary(
                    appointment.getId(),
                    appointment.getDescription(),
                    appointment.getSession().getDescription(),
                    appointment.getSession().getDateTime(),
                    appointment.getSession().getUsersLimit(),
                    appointment.getSession().getPlace().getId(),
                    appointment.getSession().getPlace().getPlaceName(),
                    appointment.getSession().getPlace().getPlaceAddress()
            ));
        });

        return ResponseEntity.ok().body(appointmentSummaries);
    }

    @PutMapping
    private ResponseEntity<AppointmentResponse> updateAppointment(@RequestBody AppointmentInput appointmentInput) {
        var user = userRetriever.getUser(appointmentInput.userId()).orElseThrow();
        var session = sessionRetriever.getSession(appointmentInput.sessionId()).orElseThrow();
        var appointmentUpdated = appointmentUpdater.updateAppointment(
                Appointment.create(appointmentInput.id(), appointmentInput.description(), user, session)
        ).orElseThrow();
        log.info("Appointment updated: {}", appointmentInput);

        return ResponseEntity.ok().body(new AppointmentResponse(
                appointmentUpdated.getId(),
                appointmentUpdated.getDescription(),
                appointmentUpdated.gettUser().getId(),
                appointmentUpdated.getSession().getId()
        ));
    }

    @DeleteMapping("/{appointmentId}")
    private ResponseEntity<Void> deleteAppointment(@PathVariable String appointmentId) {
        appointmentDeleter.deleteAppointment(appointmentId);
        log.info("Appointment deleted: {}", appointmentId);
        return ResponseEntity.noContent().build();
    }
}
