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

import java.util.Optional;

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
        user.setEmail(email);
        user.setPassword(password);
        when(userRepository.searchFirstByEmailAndPassword(email, password)).thenReturn(Optional.of(user));

        userController.login(email, password);

        Mockito.verify(authService).generateJWT(email, password);
    }
}
