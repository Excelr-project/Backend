package com.example.Backend.service;

import com.example.Backend.Entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private final Map<String, Optional<User>> tokenMap = new HashMap<>();

    public String generateToken(Optional<User> user){
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, user);
        return token;
    }

    public Optional<User> getUserFromToken(String token){
        return tokenMap.get(token);
    }
}
