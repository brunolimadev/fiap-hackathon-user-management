package br.com.fiap.usermanagement.infra.controllers.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.fiap.usermanagement.infra.controllers.dtos.error.ErrorDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings("null")
    @Test
    void handleValidationException_ShouldReturnBadRequestError() {
        // Arrange
        ExceptionAdvice exceptionAdvice = new ExceptionAdvice();

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(
                null, buildBindingResult());

        // Act
        ResponseEntity<ErrorDto> responseEntity = exceptionAdvice.handleRuntimeException(exception);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().title()).isEqualTo("Ocorreu um erro interno");
        assertThat(responseEntity.getBody().message()).isEqualTo("Erro de validação");
        assertThat(responseEntity.getBody().code()).isEqualTo("400");
    }

    private static BindingResult buildBindingResult() {
        return new BindingResult() {
            @Override
            public String getObjectName() {
                return null;
            }

            @Override
            public void setNestedPath(String nestedPath) {
            }

            @Override
            public String getNestedPath() {
                return null;
            }

            @Override
            public void pushNestedPath(String subPath) {
            }

            @Override
            public void popNestedPath() throws IllegalStateException {
            }

            @Override
            public void reject(String errorCode) {
            }

            @Override
            public void reject(String errorCode, String defaultMessage) {
            }

            @Override
            public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
            }

            @Override
            public void rejectValue(String field, String errorCode) {
            }

            @Override
            public void rejectValue(String field, String errorCode, String defaultMessage) {
            }

            @Override
            public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
            }

            @Override
            public void addAllErrors(Errors errors) {
            }

            @Override
            public boolean hasErrors() {
                return false;
            }

            @Override
            public int getErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getAllErrors() {
                return null;
            }

            @Override
            public boolean hasGlobalErrors() {
                return false;
            }

            @Override
            public int getGlobalErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            @Override
            public ObjectError getGlobalError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors() {
                return false;
            }

            @Override
            public int getFieldErrorCount() {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors() {
                FieldError fieldError = new FieldError("Teste", "Teste", "Teste");
                return Arrays.asList(fieldError);
            }

            @Override
            public FieldError getFieldError() {
                return new FieldError(NESTED_PATH_SEPARATOR, MODEL_KEY_PREFIX, NESTED_PATH_SEPARATOR);
            }

            @Override
            public boolean hasFieldErrors(String field) {
                return false;
            }

            @Override
            public int getFieldErrorCount(String field) {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors(String field) {
                return null;
            }

            @Override
            @Nullable
            public Object getFieldValue(String field) {
                return null;
            }

            @Override
            @Nullable
            public Object getTarget() {
                return null;
            }

            @Override
            public Map<String, Object> getModel() {
                return null;
            }

            @Override
            @Nullable
            public Object getRawFieldValue(String field) {
                return null;
            }

            @Override
            @Nullable
            public PropertyEditor findEditor(@Nullable String field, @Nullable Class<?> valueType) {
                return null;
            }

            @Override
            @Nullable
            public PropertyEditorRegistry getPropertyEditorRegistry() {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String errorCode) {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String errorCode, String field) {
                return null;
            }

            @Override
            public void addError(ObjectError error) {
                return;
            }
        };
    }

}