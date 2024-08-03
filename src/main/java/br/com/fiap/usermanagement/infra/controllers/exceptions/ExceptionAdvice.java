package br.com.fiap.usermanagement.infra.controllers.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.usermanagement.infra.controllers.dtos.error.ErrorDto;
import br.com.fiap.usermanagement.infra.controllers.dtos.error.ErrorFieldDto;

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

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<ErrorDto> handleRuntimeException(UserNotFoundException userNotFoundException) {
        ErrorDto errorDto = new ErrorDto(
                "Ocorreu um erro interno",
                userNotFoundException.getMessage(),
                String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), null);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDto);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorDto> handleRuntimeException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        List<ErrorFieldDto> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorFieldDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorDto errorDto = new ErrorDto(
                "Ocorreu um erro interno",
                "Erro de validação",
                String.valueOf(HttpStatus.BAD_REQUEST.value()), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

}
