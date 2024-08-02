package br.com.fiap.usermanagement.infra.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.usermanagement.infra.persistence.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByEmail(String email);
}
