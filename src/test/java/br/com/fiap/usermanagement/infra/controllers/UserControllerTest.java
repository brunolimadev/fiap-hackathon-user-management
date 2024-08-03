package br.com.fiap.usermanagement.infra.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.fiap.usermanagement.application.usecases.CreateUserInteractor;
import br.com.fiap.usermanagement.application.usecases.GetUserByEmailInteractor;
import br.com.fiap.usermanagement.domain.entities.User;
import br.com.fiap.usermanagement.domain.enums.UserRoleEnum;
import br.com.fiap.usermanagement.infra.controllers.dtos.request.UserDto;
import br.com.fiap.usermanagement.infra.controllers.dtos.response.GetUserByEmailDto;
import br.com.fiap.usermanagement.infra.controllers.exceptions.ExceptionAdvice;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;
import br.com.fiap.usermanagement.infra.enums.RoleEnum;
import br.com.fiap.usermanagement.infra.gateways.mappers.GetUserByEmailMapper;
import br.com.fiap.usermanagement.infra.gateways.mappers.UserDtoMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Import(ExceptionAdvice.class)
class UserControllerTest {

    @MockBean
    private CreateUserInteractor createUserInteractor;

    @MockBean
    private GetUserByEmailInteractor getUserByEmailInteractor;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        UserController userController = new UserController(createUserInteractor, getUserByEmailInteractor);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void whenCreateUserReturnsSuccess_thenStatus200() throws Exception {
        UserDto request = new UserDto(
                "teste",
                "test@test.com.br",
                "Teste@123",
                LocalDateTime.now(),
                UserRoleEnum.ROLE_USER);

        User user = UserDtoMapper.toDomain(request);

        when(createUserInteractor.create(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(createUserInteractor, times(1)).create(any(User.class));
    }

    @Test
    void whenGetUserByEmailReturnsSuccess_thenStatus200() throws Exception {
        GetUserByEmailDto response = new GetUserByEmailDto(
                UUID.randomUUID(),
                "teste",
                "test@test.com.br",
                "Teste@123",
                true,
                LocalDateTime.now(),
                RoleEnum.ROLE_USER);

        User user = GetUserByEmailMapper.toDomain(response);

        when(getUserByEmailInteractor.getUserByEmail(anyString())).thenReturn(user);

        mockMvc.perform(get("/users?byEmail=teste@email.com"))
                .andExpect(status().isOk());

        verify(getUserByEmailInteractor, times(1)).getUserByEmail(anyString());

    }

    @Test
    void whenGetUserByEmailThrowsUserNotFountException_thenStatus500() throws Exception {

        when(getUserByEmailInteractor.getUserByEmail(anyString()))
                .thenThrow(new UserNotFoundException("Usuário não encontrado"));

        mockMvc.perform(get("/users?byEmail=teste@email.com"))
                .andExpect(status().is5xxServerError());
    }

}
