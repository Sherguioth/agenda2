package co.edu.unibague.agenda2.user.application;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.domain.usecases.UpdateUser;

public class UserUpdater implements UpdateUser {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserUpdater(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRoleToUser(String userId, String roleName) {
        var optionalUser = userRepository.findById(new Id(userId));
        var optionalRole = roleRepository.findByName(roleName);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        Role role = optionalRole.orElseThrow(() -> new RuntimeException("Role not found"));
        user.addRole(role);
        userRepository.addRoleToUser(user, role);
    }
}
