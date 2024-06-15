package co.edu.unibague.agenda2.user.infrastructure.entities;

import co.edu.unibague.agenda2.user.domain.User;

public class UserMapper {

    private UserMapper() {}

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday()
        );
    }

    public static User toDomainUser(UserEntity userEntity) {
        return User.create(
                userEntity.getId().toString(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthday().toString()
        );
    }
}
