package br.com.fiap.usermanagement.infra.gateways;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.usermanagement.application.gateways.UserGateway;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.infra.gateways.mappers.UserEntityMapper;
import br.com.fiap.usermanagement.infra.persistence.UserRepository;
import br.com.fiap.usermanagement.infra.persistence.entities.UserEntity;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserRepositoryGateway(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {

        Optional<UserEntity> userFounded = this.userRepository.findByEmail(user.getEmail());

        if (userFounded.isPresent()) {
            throw new RuntimeException("Erro ao criar o usuário! Por favor, tente novamente.");
        }

        String encrypedPassoword = this.passwordEncoder.encode(user.getPassword());

        UserEntity userEntity = UserEntityMapper.toEntity(user);

        userEntity.setPassword(encrypedPassoword);

        UserEntity result = this.userRepository.save(userEntity);

        return UserEntityMapper.toDomain(result);
    }

    @Override
    public User getUserByEmail(String email) {

        Optional<UserEntity> userFounded = this.userRepository.findByEmail(email);

        if (!userFounded.isPresent()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        UserEntity userEntity = userFounded.get();

        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isActive(),
                userEntity.getCreatedAt(),
                userEntity.getRole());
    }

}
