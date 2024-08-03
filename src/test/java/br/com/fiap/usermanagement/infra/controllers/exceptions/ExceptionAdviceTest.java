package br.com.fiap.usermanagement.infra.controllers.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.usermanagement.infra.controllers.dtos.error.ErrorDto;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionAdviceTest {

    @SuppressWarnings("null")
    @Test
    void handleRuntimeException_ShouldReturnInternalServerError() {
        // Arrange
        ExceptionAdvice exceptionAdvice = new ExceptionAdvice();
        RuntimeException exception = new RuntimeException("Test exception");

        // Act
        ResponseEntity<ErrorDto> responseEntity = exceptionAdvice.handleRuntimeException(exception);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().title()).isEqualTo("Ocorreu um erro interno");
        assertThat(responseEntity.getBody().message()).isEqualTo("Test exception");
        assertThat(responseEntity.getBody().code()).isEqualTo("500");
    }

    @SuppressWarnings("null")
    @Test
    void handleUserNotFoundException_ShouldReturnUnprocessableEntityError() {
        // Arrange
        ExceptionAdvice exceptionAdvice = new ExceptionAdvice();
        UserNotFoundException exception = new UserNotFoundException("Test exception");

        // Act
        ResponseEntity<ErrorDto> responseEntity = exceptionAdvice.handleRuntimeException(exception);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().title()).isEqualTo("Ocorreu um erro interno");
        assertThat(responseEntity.getBody().message()).isEqualTo("Test exception");
        assertThat(responseEntity.getBody().code()).isEqualTo("422");
    }

}