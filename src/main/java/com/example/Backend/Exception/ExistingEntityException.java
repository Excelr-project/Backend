package com.example.Backend.Exception;

public class ExistingEntityException extends RuntimeException{

    public ExistingEntityException(String message){
        super(message);
    }
}
