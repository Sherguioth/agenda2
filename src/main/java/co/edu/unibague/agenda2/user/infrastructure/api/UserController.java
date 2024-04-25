package co.edu.unibague.agenda2.user.infrastructure.api;

import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;
import co.edu.unibague.agenda2.user.application.UserCreator;
import co.edu.unibague.agenda2.user.application.UserRetriever;
import co.edu.unibague.agenda2.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCreator userCreator;
    private final UserRetriever userRetriever;

    public UserController(UserCreator userCreator, UserRetriever userRetriever) {
        this.userCreator = userCreator;
        this.userRetriever = userRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserInput userInput) throws InvalidArgumentException {
        userCreator.createUser(User.userCreator(userInput.id(), userInput.email(), userInput.password(),
                userInput.firstName(), userInput.lastName(), userInput.birthday()));
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

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello";
    }
}
