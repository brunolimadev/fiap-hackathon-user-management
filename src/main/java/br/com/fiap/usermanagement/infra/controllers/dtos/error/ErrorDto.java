package br.com.fiap.usermanagement.infra.controllers.dtos.error;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ErrorDto(
                String title,
                String message,
                String code,
                @JsonInclude(JsonInclude.Include.NON_NULL) List<ErrorFieldDto> fields) {
}