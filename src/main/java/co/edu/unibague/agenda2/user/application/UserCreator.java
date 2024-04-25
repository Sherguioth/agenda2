package co.edu.unibague.agenda2.user.application;

import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;
import co.edu.unibague.agenda2.user.domain.usecases.CreateUserUseCase;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;

public class UserCreator implements CreateUserUseCase {

    private final UserRepository userRepository;

    public UserCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) throws InvalidArgumentException {
        userRepository.save(user);
    }
}
