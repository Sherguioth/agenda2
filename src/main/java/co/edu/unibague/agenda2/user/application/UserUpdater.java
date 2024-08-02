package co.edu.unibague.agenda2.user.application;


import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.category.domain.SubCategoryRepository;
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
    private final SubCategoryRepository subCategoryRepository;

    public UserUpdater(UserRepository userRepository, RoleRepository roleRepository,
                       SubCategoryRepository subCategoryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.subCategoryRepository = subCategoryRepository;
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
        var optionalSubCategory = subCategoryRepository.findByName(categoryName);

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        SubCategory category = optionalSubCategory.orElseThrow(() -> new RuntimeException("SubCategory not found"));

        var userCategory = new UserCategory(category, isAnExpert);
        user.addCategory(userCategory);
        userRepository.addCategoryToUser(user, userCategory);
    }

    @Override
    public void removeCategoryFromUser(String userId, String categoryName) {
        var subCategoryOptional = subCategoryRepository.findByName(categoryName);
        SubCategory category = subCategoryOptional.orElseThrow(() -> new RuntimeException("SubCategory not found"));

        var optionalUser = userRepository.findById(new Id(userId));
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        var userCategories = userRepository.findUserCategories(user)
                .orElseThrow(() -> new RuntimeException("UserCategory not found"));

        var userCategory = userCategories.value().stream()
                .filter(userCat -> userCat.getCategory().getId().equals(category.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("UserCategory not found"));

        user.removeCategory(userCategory);
        userRepository.removeCategoryFromUser(user, userCategory);
    }
}
