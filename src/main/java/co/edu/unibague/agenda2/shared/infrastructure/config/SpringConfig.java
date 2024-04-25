package co.edu.unibague.agenda2.shared.infrastructure.config;

import co.edu.unibague.agenda2.user.application.UserCreator;
import co.edu.unibague.agenda2.user.application.UserRetriever;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserCreator userCreator(UserRepository userRepository) {
        return new UserCreator(userRepository);
    }

    @Bean
    public UserRetriever userRetriever(UserRepository userRepository) {
        return new UserRetriever(userRepository);
    }
}
