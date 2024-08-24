package com.snippetshare.api.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String generateJWT(String email, String password) {
        // TODO: generate JWT
        return "myjwt";
    }
}
