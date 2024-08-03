package br.com.fiap.usermanagement.application.usecases;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;

@ExtendWith(MockitoExtension.class)
class CreateUserInteractorTest {

    private CreateUserInteractor createUserInteractor;

    @Mock
    private UserGateway userGateway;

    @BeforeEach
    public void setup() {
        createUserInteractor = new CreateUserInteractor(userGateway);
    }

    @Test
    void testCreateUser() {

        User request = new User("teste", "test@test.com.br", "Teste@123", LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        when(userGateway.createUser(request)).thenReturn(request);

        var result = createUserInteractor.create(request);

        Assertions.assertThat(result).isNotNull();
    }

}
