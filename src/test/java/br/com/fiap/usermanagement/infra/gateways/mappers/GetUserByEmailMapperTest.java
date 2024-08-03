package br.com.fiap.usermanagement.infra.gateways.mappers;

import org.junit.jupiter.api.Test;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.controllers.dtos.request.UserDto;
import br.com.fiap.usermanagement.infra.controllers.dtos.response.GetUserByEmailDto;
import br.com.fiap.usermanagement.infra.enums.RoleEnum;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

class GetUserByEmailMapperTest {

    @Test
    void testToDto() {
        // Arrange
        User user = new User("teste", "test@test.com.br", "Teste@123", LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        // Act
        GetUserByEmailDto userDto = GetUserByEmailMapper.toDto(user);

        // Assert
        assertThat(userDto.id()).isEqualTo(user.getId());
        assertThat(userDto.name()).isEqualTo(user.getName());
        assertThat(userDto.email()).isEqualTo(user.getEmail());
        assertThat(userDto.password()).isEqualTo(user.getPassword());
        assertThat(userDto.isActive()).isEqualTo(user.isActive());
        assertThat(userDto.createdAt()).isEqualTo(user.getCreatedAt());
        assertThat(userDto.role()).isEqualTo(RoleEnum.ROLE_USER);
    }

    @Test
    void testToDomain() {
        // Arrange
        GetUserByEmailDto userDto = new GetUserByEmailDto(
                UUID.randomUUID(),
                "teste",
                "test@test.com.br",
                "Teste@123",
                true,
                LocalDateTime.now(),
                RoleEnum.ROLE_USER);

        // Act
        User user = GetUserByEmailMapper.toDomain(userDto);

        // Assert
        assertThat(user.getId()).isEqualTo(userDto.id());
        assertThat(user.getName()).isEqualTo(userDto.name());
        assertThat(user.getEmail()).isEqualTo(userDto.email());
        assertThat(user.getPassword()).isEqualTo(userDto.password());
        assertThat(user.isActive()).isEqualTo(userDto.isActive());
        assertThat(user.getCreatedAt()).isEqualTo(userDto.createdAt());
        assertThat(user.getRole()).isEqualTo(UserRoleEnum.ROLE_USER);
    }
}