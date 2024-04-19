package com.example.Backend.service;

import org.springframework.stereotype.Service;

@Service
public class AdminSigninService {

    private final String Admin_email = "tharun.project.123@gmail.com";
    private String Admin_key;

    public void setAdminKey(String key){
        this.Admin_key = key;
    }

    public boolean signinAdmin(String email, String key){
        if (email.equals(Admin_email) && key.equals(Admin_key)){
            return true;
        }
        return false;
    }
}
