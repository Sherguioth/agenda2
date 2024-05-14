package co.edu.unibague.agenda2.role.infrastructure.api;

import co.edu.unibague.agenda2.role.application.RoleCreator;
import co.edu.unibague.agenda2.role.application.RoleRetriever;
import co.edu.unibague.agenda2.role.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleCreator roleCreator;
    private final RoleRetriever roleRetriever;

    public RoleController(RoleCreator roleCreator, RoleRetriever roleRetriever) {
        this.roleCreator = roleCreator;
        this.roleRetriever = roleRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody RoleInput roleInput) {
        roleCreator.createRole(Role.createRole(roleInput.id(), roleInput.name()));
        log.info("Role created: {}", roleInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getRoles() {
        List<Role> roleList = roleRetriever.getAllRoles();
        List<RoleResponse> roleResponses = roleList.stream().map(
                role -> new RoleResponse(role.getId(), role.getRoleName())
        ).toList();
        return ResponseEntity.ok().body(roleResponses);
    }
}
