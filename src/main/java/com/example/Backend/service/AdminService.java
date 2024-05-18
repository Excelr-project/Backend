package com.example.Backend.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminService {

    private final JavaMailSender javaMailSender;

    public AdminService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }


    private static final String Admin_email = "tharun.project.123@gmail.com";
    private static final long KEY_VALID_TIME = 5;
    private static Map<String, LocalDateTime> generatedKeys = new HashMap<>();

    public String generateAndSendKey(String email){
        if (email.equals(Admin_email)){
            String key = generateUniqueKey();
            boolean sent = sendKeyByEmail(email, key);
            if (sent){
                generatedKeys.put(key, LocalDateTime.now());
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
            } catch (MailAuthenticationException e){
                e.printStackTrace();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        return false;
    }



    public boolean isKeyValid(String key){
        LocalDateTime generationTime = generatedKeys.get(key);
        if (generationTime != null){
            LocalDateTime currentTime = LocalDateTime.now();
            long minutesRemain = generationTime.until(currentTime, ChronoUnit.MINUTES);
            return minutesRemain <= KEY_VALID_TIME;
        }
        return false;
    }
}
