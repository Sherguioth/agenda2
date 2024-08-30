package co.edu.unibague.agenda2.user.application;


import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.CategoryRepository;
import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.role.domain.RoleRepository;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.UserRepository;
import co.edu.unibague.agenda2.user.domain.usecases.UpdateUser;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategory;

public class UserUpdater implements UpdateUser {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

    public UserUpdater(UserRepository userRepository, RoleRepository roleRepository,
                       CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
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

    @Override
    public void addCategoryToUser(String userId, String categoryName, boolean isAnExpert) {
        var optionalUser = userRepository.findById(new Id(userId));
        var optionalCategory = categoryRepository.findByName(categoryName);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        Category category = optionalCategory.orElseThrow(() -> new RuntimeException("Category not found"));

        var userCategory = new UserCategory(category, isAnExpert);
        user.addCategory(userCategory);
        userRepository.addCategoryToUser(user, userCategory);
    }

    @Override
    public void removeCategoryFromUser(String userId, String categoryName) {
        var categoryOptional = categoryRepository.findByName(categoryName);
        Category category = categoryOptional.orElseThrow(() -> new RuntimeException("Category not found"));

        var optionalUser = userRepository.findById(new Id(userId));
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        var userCategories = userRepository.findUserCategories(user)
                .orElseThrow(() -> new RuntimeException("UserCategory not found"));

        var userCategory = userCategories.value().stream()
                .filter(userCat -> userCat.value().getId().equals(category.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("UserCategory not found"));

        user.removeCategory(userCategory);
        userRepository.removeCategoryFromUser(user, userCategory);
    }
}
