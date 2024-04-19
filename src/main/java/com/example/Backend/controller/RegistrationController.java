package com.example.Backend.controller;

import com.example.Backend.Entity.User;
import com.example.Backend.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signup")
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
        registrationService.registerUser(user);
        String signinUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/signin").toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, signinUri);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

}
