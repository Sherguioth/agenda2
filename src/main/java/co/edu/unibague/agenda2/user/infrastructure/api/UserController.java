package co.edu.unibague.agenda2.user.infrastructure.api;

import co.edu.unibague.agenda2.user.application.UserCreator;
import co.edu.unibague.agenda2.user.application.UserRetriever;
import co.edu.unibague.agenda2.user.application.UserUpdater;
import co.edu.unibague.agenda2.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCreator userCreator;
    private final UserRetriever userRetriever;
    private final UserUpdater userUpdater;

    public UserController(UserCreator userCreator, UserRetriever userRetriever, UserUpdater userUpdater) {
        this.userCreator = userCreator;
        this.userRetriever = userRetriever;
        this.userUpdater = userUpdater;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserInput userInput) {
        userCreator.createUser(User.userCreator(userInput.id(), userInput.email(), userInput.password(),
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
        return ResponseEntity.ok().body(userResponses);
    }

    @PutMapping("/role")
    public ResponseEntity<Void> addRoleToUser(@RequestBody UserRoleInput userRoleInput) {
        userUpdater.addRoleToUser(userRoleInput.userId(), userRoleInput.roleName());
        log.info("Role {} added to user {}", userRoleInput.userId(), userRoleInput.roleName());
        return ResponseEntity.ok().build();
    }
}
