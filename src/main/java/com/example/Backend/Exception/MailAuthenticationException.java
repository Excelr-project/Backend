package com.example.Backend.Exception;

public class MailAuthenticationException extends RuntimeException {

    public MailAuthenticationException(String message) {
        super(message);
    }

}