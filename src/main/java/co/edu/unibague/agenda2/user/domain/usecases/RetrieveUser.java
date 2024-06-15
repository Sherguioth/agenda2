package co.edu.unibague.agenda2.user.domain.usecases;

import co.edu.unibague.agenda2.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface RetrieveUser {

    Optional<User> getUser(String userId);

    List<User> getAllUsers();

}
