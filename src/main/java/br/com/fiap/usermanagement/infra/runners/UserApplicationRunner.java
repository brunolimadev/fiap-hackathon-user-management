package br.com.fiap.usermanagement.infra.runners;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.fiap.usermanagement.application.usecases.CreateUserInteractor;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserApplicationRunner implements CommandLineRunner {

    private final CreateUserInteractor createUserInteractor;

    public UserApplicationRunner(CreateUserInteractor userInteractor) {
        this.createUserInteractor = userInteractor;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating default user");

        User userDefault = new User(
                "adj2",
                "adj2@fiap.com.br",
                "adj2@1234",
                LocalDateTime.now(),
                UserRoleEnum.ROLE_ADMIN);

        log.info("User {} created succesfully", userDefault.getEmail());
        try {
            this.createUserInteractor.create(userDefault);
        } catch (UserNotFoundException e) {
            log.info("User {} has already created", userDefault.getEmail());
        }

    }

}
