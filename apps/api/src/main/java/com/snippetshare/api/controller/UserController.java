package com.snippetshare.api.controller;

import com.snippetshare.api.entity.User;
import com.snippetshare.api.repository.UserRepository;
import com.snippetshare.api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public UserController(
            AuthService authService,
            UserRepository userRepository
    ) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ) {
        var user = userRepository.searchFirstByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var jwt = authService.generateJWT(user.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {
        var user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        try {
            var createdUser = userRepository.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
