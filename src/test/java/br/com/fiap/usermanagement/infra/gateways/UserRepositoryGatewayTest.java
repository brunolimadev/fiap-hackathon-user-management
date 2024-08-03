package br.com.fiap.usermanagement.infra.gateways;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;
import br.com.fiap.usermanagement.infra.persistence.UserRepository;
import br.com.fiap.usermanagement.infra.persistence.entities.UserEntity;

class UserRepositoryGatewayTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRepositoryGateway userRepositoryGateway;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() throws UserNotFoundException {
        // Arrange
        User user = new User("teste", "test@test.com.br", "Teste@123", LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity userEntity = invocation.getArgument(0);
            userEntity.setId(UUID.randomUUID());
            return userEntity;
        });

        // Act
        User result = userRepositoryGateway.createUser(user);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getPassword()).isEqualTo("encodedPassword");
        assertThat(result.isActive()).isTrue();
        assertThat(result.getCreatedAt()).isEqualTo(user.getCreatedAt());
        assertThat(result.getRole()).isEqualTo(user.getRole());

        verify(userRepository).findByEmail(user.getEmail());
        verify(passwordEncoder).encode(user.getPassword());
        verify(userRepository).save(userEntityCaptor.capture());

        UserEntity savedUserEntity = userEntityCaptor.getValue();
        assertThat(savedUserEntity).isNotNull();
        assertThat(savedUserEntity.getId()).isNotNull();
        assertThat(savedUserEntity.getName()).isEqualTo(user.getName());
        assertThat(savedUserEntity.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUserEntity.getPassword()).isEqualTo("encodedPassword");
        assertThat(savedUserEntity.isActive()).isTrue();
        assertThat(savedUserEntity.getCreatedAt()).isEqualTo(user.getCreatedAt());
        assertThat(savedUserEntity.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void testCreateUser_ThrowsUserNotFoundException() {
        // Arrange
        User user = new User("teste", "test@test.com.br", "Teste@123", LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(new UserEntity()));

        // Act & Assert
        assertThatThrownBy(() -> userRepositoryGateway.createUser(user))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Erro ao criar o usuário! Por favor, tente novamente.");

        verify(userRepository).findByEmail(user.getEmail());
        verifyNoMoreInteractions(passwordEncoder, userRepository);
    }

    @Test
    void testGetUserByEmail() throws UserNotFoundException {
        // Arrange
        String email = "test@test.com.br";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setName("teste");
        userEntity.setEmail(email);
        userEntity.setPassword("Teste@123");
        userEntity.setActive(true);
        userEntity.setCreatedAt(LocalDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));

        // Act
        User result = userRepositoryGateway.getUserByEmail(email);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userEntity.getId());
        assertThat(result.getName()).isEqualTo(userEntity.getName());
        assertThat(result.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(result.getPassword()).isEqualTo(userEntity.getPassword());
        assertThat(result.isActive()).isEqualTo(userEntity.isActive());
        assertThat(result.getCreatedAt()).isEqualTo(userEntity.getCreatedAt());
        assertThat(result.getRole()).isEqualTo(userEntity.getRole());

        verify(userRepository).findByEmail(email);
        verifyNoMoreInteractions(passwordEncoder, userRepository);
    }

    @Test
    void testGetUserByEmail_ThrowsUserNotFoundException() {
        // Arrange
        String email = "test@test.com.br";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> userRepositoryGateway.getUserByEmail(email))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Usuário não encontrado!");

        verify(userRepository).findByEmail(email);
        verifyNoMoreInteractions(passwordEncoder, userRepository);
    }
}