package co.edu.unibague.agenda2.shared.infrastructure.config;

import co.edu.unibague.agenda2.role.application.RoleCreator;
import co.edu.unibague.agenda2.role.application.RoleRetriever;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.schedule.application.ScheduleCreator;
import co.edu.unibague.agenda2.schedule.application.ScheduleRetriever;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.user.application.UserCreator;
import co.edu.unibague.agenda2.user.application.UserRetriever;
import co.edu.unibague.agenda2.user.application.UserUpdater;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeansConfig {

    @Bean
    public UserCreator userCreator(UserRepository userRepository) {
        return new UserCreator(userRepository);
    }

    @Bean
    public UserRetriever userRetriever(UserRepository userRepository) {
        return new UserRetriever(userRepository);
    }

    @Bean
    public UserUpdater userUpdater(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserUpdater(userRepository, roleRepository);
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
}
