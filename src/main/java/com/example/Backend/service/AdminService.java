package com.example.Backend.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private final JavaMailSender javaMailSender;
    public AdminService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    private static final String Admin_email = "tharun.project.123@gmail.com";

    public String generateAndSendKey(String email){

        if (email.equals(Admin_email)){
            String key = generateUniqueKey();
            boolean sent = sendKeyByEmail(email, key);

            if (sent){
                return key;
            }
        }

        return null;
    }


    private String generateUniqueKey(){

        UUID uuid = UUID.randomUUID();
        String uniqueKey = uuid.toString();
        return uniqueKey;
    }


    private boolean sendKeyByEmail(String email, String key){

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setTo(email);
            helper.setSubject("Your Generated Key");
            helper.setText("Generated key is :" + key);

            javaMailSender.send(message);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
