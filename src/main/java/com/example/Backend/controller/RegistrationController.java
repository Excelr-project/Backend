package com.example.Backend.controller;

import com.example.Backend.Entity.User;
import com.example.Backend.Exception.ExistingEntityException;
import com.example.Backend.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user){
//        User registereduser = registrationService.registerUser(user);
//        return new ResponseEntity<>(registereduser, HttpStatus.CREATED);
        try {
            Integer userId = registrationService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId); // Use CREATED for successful creation
        } catch (ExistingEntityException e) {
            return ResponseEntity.badRequest().build(); // Handle existing email case
        }
    }
}