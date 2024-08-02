package br.com.fiap.usermanagement.infra.gateways.mappers;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.infra.persistence.entities.UserEntity;

public class UserEntityMapper {
    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getRole());
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isActive(),
                userEntity.getCreatedAt(),
                userEntity.getRole());
    }
}
