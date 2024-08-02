package co.edu.unibague.agenda2.user.domain.usecases;

public interface UpdateUser {

    void addRoleToUser(String userId, String roleName);

    void addCategoryToUser(String userId, String categoryName, boolean isAnExpert);

    void removeCategoryFromUser(String userId, String categoryName);
}
