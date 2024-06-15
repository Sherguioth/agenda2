package co.edu.unibague.agenda2.user.application;

import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.usecases.RetrieveUser;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRetriever implements RetrieveUser {

    private final UserRepository userRepository;

    public UserRetriever(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.findById(new Id(userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
