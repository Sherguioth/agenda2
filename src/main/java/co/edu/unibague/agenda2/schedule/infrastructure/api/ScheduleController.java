package co.edu.unibague.agenda2.schedule.infrastructure.api;

import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.usecases.CreateSchedule;
import co.edu.unibague.agenda2.schedule.domain.usecases.RetrieveSchedule;
import co.edu.unibague.agenda2.schedule.domain.usecases.UpdateSchedule;
import co.edu.unibague.agenda2.user.domain.exceptions.UserNotFoundException;
import co.edu.unibague.agenda2.user.domain.usecases.RetrieveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final CreateSchedule scheduleCreator;
    private final RetrieveSchedule scheduleRetriever;
    private final UpdateSchedule scheduleUpdater;
    private final RetrieveUser userRetriever;

    public ScheduleController(CreateSchedule scheduleCreator, RetrieveSchedule scheduleRetriever, UpdateSchedule scheduleUpdater, RetrieveUser userRetriever) {
        this.scheduleCreator = scheduleCreator;
        this.scheduleRetriever = scheduleRetriever;
        this.scheduleUpdater = scheduleUpdater;
        this.userRetriever = userRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleInput scheduleInput) {
        var user = userRetriever.getUser(scheduleInput.professionalId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        scheduleCreator.createSchedule(Schedule.create(scheduleInput.id(), scheduleInput.title(), user));
        log.info("Created Schedule: {}", scheduleInput.title());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getSchedules() {
        List<Schedule> schedules = scheduleRetriever.getAllSchedules();
        List<ScheduleResponse> scheduleResponses = schedules.stream().map(
                schedule -> new ScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getProfessional().getId())
        ).toList();
        log.info("Retrieved Schedules: {}", scheduleResponses.size());
        return ResponseEntity.ok().body(scheduleResponses);
    }

    @PutMapping("/category")
    public ResponseEntity<Void> addCategoryToSchedule(@RequestBody ScheduleCategoryInput scheduleCategoryInput) {
        scheduleUpdater.addCategoryToSchedule(
                scheduleCategoryInput.scheduleId(),
                scheduleCategoryInput.categoryName(),
                scheduleCategoryInput.isMandatory()
        );
        log.info("Category {} added to schedule {}", scheduleCategoryInput.categoryName(), scheduleCategoryInput.scheduleId());
        return ResponseEntity.ok().build();
    }
}
