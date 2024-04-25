package co.edu.unibague.agenda2.user.domain.usecases;

import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;
import co.edu.unibague.agenda2.user.domain.User;

public interface CreateUserUseCase {

    void createUser(User user) throws InvalidArgumentException;
}
