package br.com.fiap.usermanagement.application.gateways;

import br.com.fiap.usermanagement.domain.entities.User;

public interface UserGateway {
    User createUser(User user);

    User getUserByEmail(String email);
}
