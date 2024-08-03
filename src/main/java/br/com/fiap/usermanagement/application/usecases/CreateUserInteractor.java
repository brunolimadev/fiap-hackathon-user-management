package br.com.fiap.usermanagement.application.usecases;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;

public class CreateUserInteractor {

    private final UserGateway userGateway;

    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User create(User user) throws UserNotFoundException {
        return userGateway.createUser(user);
    }

}
