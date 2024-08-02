package br.com.fiap.usermanagement.infra.controllers.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fiap.usermanagement.infra.enums.RoleEnum;

public record GetUserByEmailDto(
        UUID id,
        String name,
        String email,
        String password,
        boolean isActive,
        LocalDateTime createdAt,
        RoleEnum role) {

}
