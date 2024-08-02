package br.com.fiap.usermanagement.infra.gateways.mappers;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.infra.controllers.dtos.request.UserDto;

public class UserDtoMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getRole());
    }

    public static User toDomain(UserDto userDto) {
        return new User(
                userDto.name(),
                userDto.email(),
                userDto.password(),
                userDto.createdAt(),
                userDto.role());
    }

}
