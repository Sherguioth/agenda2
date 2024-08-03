package co.edu.unibague.agenda2.role.infrastructure.api;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.usecases.CreateRole;
import co.edu.unibague.agenda2.role.domain.usecases.RetrieveRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final CreateRole roleCreator;
    private final RetrieveRole roleRetriever;

    public RoleController(CreateRole roleCreator, RetrieveRole roleRetriever) {
        this.roleCreator = roleCreator;
        this.roleRetriever = roleRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody RoleInput roleInput) {
        roleCreator.createRole(Role.create(roleInput.id(), roleInput.name()));
        log.info("Role created: {}", roleInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getRoles() {
        List<Role> roleList = roleRetriever.getAllRoles();
        List<RoleResponse> roleResponses = roleList.stream().map(
                role -> new RoleResponse(role.getId(), role.getName())
        ).toList();
        log.info("Roles retrieved: {}", roleResponses.size());
        return ResponseEntity.ok().body(roleResponses);
    }
}
