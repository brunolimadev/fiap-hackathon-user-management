package br.com.fiap.usermanagement.infra.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.usermanagement.infra.controllers.dtos.error.ErrorDto;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException runtimeException) {
        ErrorDto errorDto = new ErrorDto(
                "Ocorreu um erro interno",
                runtimeException.getMessage(),
                "500", null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

}
