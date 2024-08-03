package br.com.fiap.usermanagement.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.application.usecases.CreateUserInteractor;
import br.com.fiap.usermanagement.application.usecases.GetUserByEmailInteractor;
import br.com.fiap.usermanagement.infra.gateways.UserRepositoryGateway;
import br.com.fiap.usermanagement.infra.persistence.UserRepository;

@Configuration
public class UserConfig {

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

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http

                // Enable Basic Authentication with Default Configuration
                .httpBasic(Customizer.withDefaults())

                // Disable CSRF and Session Management to use Basic Authentication
                .csrf(csrf -> csrf.disable())

                // Disable Session Management to use Basic Authentication
                .sessionManagement(AbstractHttpConfigurer::disable)

                // Authorize Requests
                .authorizeHttpRequests(
                        authorizize -> {
                            authorizize
                                    .anyRequest().permitAll();
                        })
                .build();

    }

}
