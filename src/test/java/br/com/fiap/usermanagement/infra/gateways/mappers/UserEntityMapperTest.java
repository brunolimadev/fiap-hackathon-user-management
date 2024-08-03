package br.com.fiap.usermanagement.infra.gateways.mappers;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserEntityMapperTest {

    @Test
    void testToEntity() {
        // Arrange
        User user = new User(
                "John Doe",
                "john.doe@example.com",
                "password",
                LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        // Act
        UserEntity userEntity = UserEntityMapper.toEntity(user);

        // Assert
        assertThat(userEntity.getName()).isEqualTo(user.getName());
        assertThat(userEntity.getEmail()).isEqualTo(user.getEmail());
        assertThat(userEntity.getPassword()).isEqualTo(user.getPassword());
        assertThat(userEntity.getCreatedAt()).isEqualTo(user.getCreatedAt());
        assertThat(userEntity.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void testToDomain() {
        // Arrange
        UserEntity userEntity = new UserEntity(
                UUID.randomUUID(),
                "John Doe",
                "john.doe@example.com",
                "password",
                true,
                LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        // Act
        User user = UserEntityMapper.toDomain(userEntity);

        // Assert
        assertThat(user.getId()).isEqualTo(userEntity.getId());
        assertThat(user.getName()).isEqualTo(userEntity.getName());
        assertThat(user.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(user.getPassword()).isEqualTo(userEntity.getPassword());
        assertThat(user.isActive()).isEqualTo(userEntity.isActive());
        assertThat(user.getCreatedAt()).isEqualTo(userEntity.getCreatedAt());
        assertThat(user.getRole()).isEqualTo(userEntity.getRole());
    }
}