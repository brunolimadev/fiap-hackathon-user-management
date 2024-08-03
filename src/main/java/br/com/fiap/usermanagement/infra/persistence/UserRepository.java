package br.com.fiap.usermanagement.infra.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.usermanagement.infra.persistence.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByEmail(String email);
}
