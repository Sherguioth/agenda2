package co.edu.unibague.agenda2.user.infrastructure.api;

public record UserInput(String id, String email, String password,String firstName, String lastName, String birthday) {
}
