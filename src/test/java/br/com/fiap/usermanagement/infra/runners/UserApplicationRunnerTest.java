package br.com.fiap.usermanagement.infra.runners;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.usermanagement.application.usecases.CreateUserInteractor;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureTestDatabase
@Slf4j
class UserApplicationRunnerTest {

    @Mock
    private CreateUserInteractor createUserInteractor;

    private UserApplicationRunner userApplicationRunner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userApplicationRunner = new UserApplicationRunner(createUserInteractor);
    }

    @Test
    void testRun() throws Exception {
        // Arrange
        User userDefault = new User(
                "adj2",
                "adj2@fiap.com.br",
                "adj@1234",
                LocalDateTime.now(),
                UserRoleEnum.ROLE_ADMIN);

        // Mock the behavior of the createUserInteractor
        when(createUserInteractor.create(userDefault)).thenAnswer(i -> {
            return i.getArgument(0);
        });

        // Act
        userApplicationRunner.run();

        // Assert
        verify(createUserInteractor, times(1)).create(any(User.class));
        assertThat(userDefault.getEmail()).isEqualTo("adj2@fiap.com.br");
    }

}