package com.example.Backend.controller;

import com.example.Backend.Entity.User;
import com.example.Backend.service.RegistrationService;
import com.example.Backend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ForgotPasswordController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/ForgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> body){
        String email = body.get("email");
        Optional<User> user = registrationService.findByEmail(email);

        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with given email not found");
        }
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok("Password reset link sent to your email");
    }


    @PostMapping("/ResetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> body){
        String token = body.get("token");
        String newPassword = body.get("NewPassword");
        String confirmPassword = body.get("confirmPassword");

        Optional<User> user = tokenService.getUserFromToken(token);

        if (user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
        }

        registrationService.updatePassword(user, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
}
