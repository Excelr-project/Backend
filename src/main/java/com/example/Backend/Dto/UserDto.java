package com.example.Backend.Dto;

import lombok.Data;

@Data
public class UserDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmpassword;

    private Integer phone;
}
