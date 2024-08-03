package br.com.fiap.usermanagement.infra.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.usermanagement.application.usecases.CreateUserInteractor;
import br.com.fiap.usermanagement.application.usecases.GetUserByEmailInteractor;
import br.com.fiap.usermanagement.infra.controllers.dtos.request.UserDto;
import br.com.fiap.usermanagement.infra.controllers.dtos.response.GetUserByEmailDto;
import br.com.fiap.usermanagement.infra.controllers.exceptions.UserNotFoundException;
import br.com.fiap.usermanagement.infra.gateways.mappers.GetUserByEmailMapper;
import br.com.fiap.usermanagement.infra.gateways.mappers.UserDtoMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final GetUserByEmailInteractor getUserByEmailInteractor;

    public UserController(CreateUserInteractor userInteractor, GetUserByEmailInteractor getUserByEmailInteractor) {
        this.createUserInteractor = userInteractor;
        this.getUserByEmailInteractor = getUserByEmailInteractor;
    }

    @GetMapping
    public ResponseEntity<GetUserByEmailDto> getByEmail(@RequestParam("byEmail") String email)
            throws Exception {

        GetUserByEmailDto response = GetUserByEmailMapper.toDto(getUserByEmailInteractor.getUserByEmail(email));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDto request) throws UserNotFoundException {

        createUserInteractor.create(UserDtoMapper.toDomain(request));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
