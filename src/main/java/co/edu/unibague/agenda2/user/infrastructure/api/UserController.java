package co.edu.unibague.agenda2.user.infrastructure.api;

import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.usecases.CreateUser;
import co.edu.unibague.agenda2.user.domain.usecases.RetrieveUser;
import co.edu.unibague.agenda2.user.domain.usecases.UpdateUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUser userCreator;
    private final RetrieveUser userRetriever;
    private final UpdateUser userUpdater;

    public UserController(CreateUser userCreator, RetrieveUser userRetriever, UpdateUser userUpdater) {
        this.userCreator = userCreator;
        this.userRetriever = userRetriever;
        this.userUpdater = userUpdater;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserInput userInput) {
        userCreator.createUser(User.create(userInput.id(), userInput.email(), userInput.password(),
                userInput.firstName(), userInput.lastName(), userInput.birthday()));
        log.info("User created: {}", userInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        var optionalUser = userRetriever.getUser(id);
        return optionalUser.map(user -> {
            var userResponse = new UserResponse(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthday()
            );
            log.info("User retrieved: {} {}", user.getFirstName(), user.getLastName());
            return ResponseEntity.ok().body(userResponse);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> userList = userRetriever.getAllUsers();
        List<UserResponse> userResponses = userList.stream().map(
                user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthday())
        ).toList();
        log.info("Users retrieved: {}", userResponses.size());
        return ResponseEntity.ok().body(userResponses);
    }

    @PutMapping("/role")
    public ResponseEntity<Void> addRoleToUser(@RequestBody UserRoleInput userRoleInput) {
        userUpdater.addRoleToUser(userRoleInput.userId(), userRoleInput.roleName());
        log.info("Role {} added to user {}", userRoleInput.roleName(), userRoleInput.userId());
        return ResponseEntity.ok().build();
    }
}
