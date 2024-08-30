package co.edu.unibague.agenda2.shared.infrastructure.config;

import co.edu.unibague.agenda2.appointment.application.AppointmentCreator;
import co.edu.unibague.agenda2.appointment.application.AppointmentDeleter;
import co.edu.unibague.agenda2.appointment.application.AppointmentRetriever;
import co.edu.unibague.agenda2.appointment.application.AppointmentUpdater;
import co.edu.unibague.agenda2.appointment.domain.AppointmentRepository;
import co.edu.unibague.agenda2.category.application.CategoryGroupCreator;
import co.edu.unibague.agenda2.category.application.CategoryGroupRetriever;
import co.edu.unibague.agenda2.category.application.CategoryCreator;
import co.edu.unibague.agenda2.category.application.CategoryRetriever;
import co.edu.unibague.agenda2.category.domain.CategoryGroupRepository;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.place.application.PlaceCreator;
import co.edu.unibague.agenda2.place.application.PlaceRetriever;
import co.edu.unibague.agenda2.place.domain.PlaceRepository;
import co.edu.unibague.agenda2.role.application.RoleCreator;
import co.edu.unibague.agenda2.role.application.RoleRetriever;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.schedule.application.ScheduleCreator;
import co.edu.unibague.agenda2.schedule.application.ScheduleRetriever;
import co.edu.unibague.agenda2.schedule.application.ScheduleUpdater;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.session.application.SessionCreator;
import co.edu.unibague.agenda2.session.application.SessionRetriever;
import co.edu.unibague.agenda2.session.domain.SessionRepository;
import co.edu.unibague.agenda2.user.application.UserCreator;
import co.edu.unibague.agenda2.user.application.UserRetriever;
import co.edu.unibague.agenda2.user.application.UserUpdater;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBeansConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

    @Bean
    public UserCreator userCreator(UserRepository userRepository) {
        return new UserCreator(userRepository);
    }

    @Bean
    public UserRetriever userRetriever(UserRepository userRepository) {
        return new UserRetriever(userRepository);
    }

    @Bean
    public UserUpdater userUpdater(UserRepository userRepository, RoleRepository roleRepository,
                                   CategoryRepository categoryRepository) {
        return new UserUpdater(userRepository, roleRepository, categoryRepository);
    }

    @Bean
    public RoleCreator roleCreator(RoleRepository roleRepository) {
        return new RoleCreator(roleRepository);
    }

    @Bean
    public RoleRetriever roleRetriever(RoleRepository roleRepository) {
        return new RoleRetriever(roleRepository);
    }

    @Bean
    public ScheduleCreator scheduleCreator(ScheduleRepository scheduleRepository) {
        return new ScheduleCreator(scheduleRepository);
    }

    @Bean
    public ScheduleRetriever scheduleRetriever(ScheduleRepository scheduleRepository) {
        return new ScheduleRetriever(scheduleRepository);
    }

    @Bean
    public ScheduleUpdater scheduleUpdater(ScheduleRepository scheduleRepository, CategoryRepository categoryRepository) {
        return new ScheduleUpdater(scheduleRepository, categoryRepository);
    }

    @Bean
    public PlaceCreator placeCreator(PlaceRepository placeRepository) {
        return new PlaceCreator(placeRepository);
    }

    @Bean
    public PlaceRetriever placeRetriever(PlaceRepository placeRepository) {
        return new PlaceRetriever(placeRepository);
    }

    @Bean
    public SessionCreator sessionCreator(SessionRepository sessionRepository) {
        return new SessionCreator(sessionRepository);
    }

    @Bean
    public SessionRetriever sessionRetriever(SessionRepository sessionRepository) {
        return new SessionRetriever(sessionRepository);
    }

    @Bean
    public AppointmentCreator appointmentCreator(AppointmentRepository appointmentRepository) {
        return new AppointmentCreator(appointmentRepository);
    }

    @Bean
    public AppointmentRetriever appointmentRetriever(AppointmentRepository appointmentRepository) {
        return new AppointmentRetriever(appointmentRepository);
    }

    @Bean
    public AppointmentUpdater appointmentUpdater(AppointmentRepository appointmentRepository) {
        return new AppointmentUpdater(appointmentRepository);
    }

    @Bean
    public AppointmentDeleter appointmentDeleter(AppointmentRepository appointmentRepository) {
        return new AppointmentDeleter(appointmentRepository);
    }

    @Bean
    public CategoryGroupCreator categoryGroupCreator(CategoryGroupRepository categoryGroupRepository) {
        return new CategoryGroupCreator(categoryGroupRepository);
    }

    @Bean
    public CategoryGroupRetriever categoryGroupRetriever(CategoryGroupRepository categoryGroupRepository) {
        return new CategoryGroupRetriever(categoryGroupRepository);
    }

    @Bean
    public CategoryCreator categoryCreator(CategoryRepository categoryRepository) {
        return new CategoryCreator(categoryRepository);
    }

    @Bean
    public CategoryRetriever categoryRetriever(CategoryRepository categoryRepository) {
        return new CategoryRetriever(categoryRepository);
    }
}
