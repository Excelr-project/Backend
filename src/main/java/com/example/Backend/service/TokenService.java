package com.example.Backend.service;

import com.example.Backend.Entity.User;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JavaMailSender javaMailSender;

    private final Map<String, Optional<User>> tokenMap = new HashMap<>();

    public String generateToken(Optional<User> user){
        String token = generateRandomPIN();
        tokenMap.put(token, user);
        sendTokenEmail(user.get().getEmail(), token);
        return token;
    }

    private String generateRandomPIN(){
        Random random = new Random();
        int pin = 100000 + random.nextInt(900000);
        return String.valueOf(pin);
    }

    private void sendTokenEmail(String email, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password reset pin");
        message.setText("Your password reset rin is :" + token);
        javaMailSender.send(message);
    }

    public Optional<User> getUserFromToken(String token){
        return tokenMap.get(token);
    }
}
