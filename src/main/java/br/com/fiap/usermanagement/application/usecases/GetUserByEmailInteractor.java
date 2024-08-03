package br.com.fiap.usermanagement.application.usecases;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;

public class GetUserByEmailInteractor {

    private final UserGateway userGateway;

    public GetUserByEmailInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userGateway.getUserByEmail(email);
    }

}
