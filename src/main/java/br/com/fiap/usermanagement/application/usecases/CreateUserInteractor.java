package br.com.fiap.usermanagement.application.usecases;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.domain.entities.User;

public class CreateUserInteractor {

    private final UserGateway userGateway;

    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User create(User user) {
        return userGateway.createUser(user);
    }

}
