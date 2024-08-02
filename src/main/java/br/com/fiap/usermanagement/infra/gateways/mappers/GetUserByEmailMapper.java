package br.com.fiap.usermanagement.infra.gateways.mappers;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.controllers.dtos.response.GetUserByEmailDto;
import br.com.fiap.usermanagement.infra.enums.RoleEnum;

public class GetUserByEmailMapper {
    public static GetUserByEmailDto toDto(User user) {
        return new GetUserByEmailDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                user.getCreatedAt(),
                RoleEnum.valueOf(user.getRole().name()));
    }

    public static User toDomain(GetUserByEmailDto userDto) {
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.email(),
                userDto.password(),
                userDto.isActive(),
                userDto.createdAt(),
                UserRoleEnum.valueOf(userDto.role().name()));
    }
}
