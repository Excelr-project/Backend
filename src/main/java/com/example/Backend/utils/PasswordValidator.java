package com.example.Backend.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    Pattern pattern = Pattern.compile(regex);
}
