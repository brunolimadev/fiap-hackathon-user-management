package br.com.fiap.usermanagement.application.gateways;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;

public interface UserGateway {
    User createUser(User user) throws UserNotFoundException;

    User getUserByEmail(String email) throws UserNotFoundException;
}
