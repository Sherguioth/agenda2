package co.edu.unibague.agenda2.user.infrastructure.entities;

import co.edu.unibague.agenda2.shared.domain.exceptions.InvalidArgumentException;
import co.edu.unibague.agenda2.user.domain.User;

public class UserMapper {

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

    public static User toDomainUser(UserEntity userEntity) throws InvalidArgumentException {
        return User.userCreator(
                userEntity.getId().toString(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthday().toString()
        );
    }
}
