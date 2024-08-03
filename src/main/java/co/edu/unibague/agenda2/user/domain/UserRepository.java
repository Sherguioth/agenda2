package co.edu.unibague.agenda2.user.domain;

import co.edu.unibague.agenda2.role.domain.Role;
import co.edu.unibague.agenda2.shared.domain.Id;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategories;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategory;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Id id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    Optional<UserCategories> findUserCategories(User user);

    void addRoleToUser(User user, Role role);

    void addCategoryToUser(User user, UserCategory category);

    void removeCategoryFromUser(User user, UserCategory category);
}
