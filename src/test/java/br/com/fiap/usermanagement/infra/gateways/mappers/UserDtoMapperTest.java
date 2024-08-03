package br.com.fiap.usermanagement.infra.gateways.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.controllers.dtos.request.UserDto;

class UserDtoMapperTest {

    @Test
    void testToDto() {
        // Arrange
        User user = new User("John Doe", "john.doe@example.com", "password", LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        // Act
        UserDto userDto = UserDtoMapper.toDto(user);

        // Assert
        assertThat(userDto.name()).isEqualTo(user.getName());
        assertThat(userDto.email()).isEqualTo(user.getEmail());
        assertThat(userDto.password()).isEqualTo(user.getPassword());
        assertThat(userDto.createdAt()).isEqualTo(user.getCreatedAt());
        assertThat(userDto.role()).isEqualTo(user.getRole());
    }

    @Test
    void testToDomain() {
        // Arrange
        UserDto userDto = new UserDto(
                "John Doe",
                "john.doe@example.com",
                "password",
                LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        // Act
        User user = UserDtoMapper.toDomain(userDto);

        // Assert
        assertThat(user.getName()).isEqualTo(userDto.name());
        assertThat(user.getEmail()).isEqualTo(userDto.email());
        assertThat(user.getPassword()).isEqualTo(userDto.password());
        assertThat(user.getCreatedAt()).isEqualTo(userDto.createdAt());
        assertThat(user.getRole()).isEqualTo(userDto.role());
    }
}