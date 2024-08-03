package co.edu.unibague.agenda2.user.infrastructure.api;

public record UserCategoryInput(String userId, String categoryName, boolean isAnExpert) {
}
