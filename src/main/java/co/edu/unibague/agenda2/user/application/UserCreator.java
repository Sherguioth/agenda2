package co.edu.unibague.agenda2.user.application;

import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.domain.usecases.CreateUser;

public class UserCreator implements CreateUser {

    private final UserRepository repository;

    public UserCreator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(User user) {
        repository.save(user);
    }
}
