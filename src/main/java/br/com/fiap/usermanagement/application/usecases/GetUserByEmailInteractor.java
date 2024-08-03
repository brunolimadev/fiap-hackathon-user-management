package br.com.fiap.usermanagement.application.usecases;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.domain.entities.User;

public class GetUserByEmailInteractor {

    private final UserGateway userGateway;

    public GetUserByEmailInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User getUserByEmail(String email) {
        return userGateway.getUserByEmail(email);
    }

}
