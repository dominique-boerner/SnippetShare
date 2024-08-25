package com.snippetshare.api.service;

import com.snippetshare.api.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String generateJWT(User user) {
        // TODO: generate key via private key from configuration file
        var key = Jwts.SIG.HS256.key().build();

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .signWith(key)
                .compact();
    }
}
