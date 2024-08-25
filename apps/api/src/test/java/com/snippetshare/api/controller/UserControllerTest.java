package com.snippetshare.api.controller;

import com.snippetshare.api.entity.User;
import com.snippetshare.api.repository.UserRepository;
import com.snippetshare.api.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    AuthService authService;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginWithValidCredentials() {
        var email = "john.doe@example.com";
        var password = "mypassword";
        var user = new User();
        var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm...";
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.searchFirstByEmailAndPassword(email, password)).thenReturn(Optional.of(user));
        when(authService.generateJWT(user)).thenReturn(jwt);

        var response = userController.login(email, password);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(jwt);
    }
}
