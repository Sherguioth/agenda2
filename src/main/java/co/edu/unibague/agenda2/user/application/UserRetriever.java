package co.edu.unibague.agenda2.user.application;

import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.usecases.RetrieveUser;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRetriever implements RetrieveUser {

    private final UserRepository repository;

    public UserRetriever(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getUser(String userId) {
        return repository.findById(new Id(userId));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
