package br.com.fiap.usermanagement.infra.persistence.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private boolean isActive;

    private LocalDateTime createdAt;

    private UserRoleEnum role;

    public UserEntity(
            String name,
            String email,
            String password,
            LocalDateTime createdAt,
            UserRoleEnum role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.role = role;
    }

}
