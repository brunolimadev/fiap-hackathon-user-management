package br.com.fiap.usermanagement.main;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.application.usecases.CreateUserInteractor;
import br.com.fiap.usermanagement.application.usecases.GetUserByEmailInteractor;
import br.com.fiap.usermanagement.infra.gateways.UserRepositoryGateway;
import br.com.fiap.usermanagement.infra.persistence.UserRepository;

@TestConfiguration
public class UserConfigTest {
    @Bean
    CreateUserInteractor createUserInteractor(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    GetUserByEmailInteractor getUserByEmailInteractor(UserGateway userGateway) {
        return new GetUserByEmailInteractor(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserRepositoryGateway(userRepository, passwordEncoder);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
