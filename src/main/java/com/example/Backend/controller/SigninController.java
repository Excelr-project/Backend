package com.example.Backend.controller;

import com.example.Backend.Dto.UserDto;
import com.example.Backend.Entity.User;
import com.example.Backend.service.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signin")
@CrossOrigin(origins = "http://localhost:3000")
public class SigninController {

    private final SigninService signinService;


    @GetMapping()
    public ResponseEntity<UserDto> signin(@RequestParam String email, @RequestParam String password) {
        Optional<User> optionalUser = signinService.signinUser(email, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto userDTO = new UserDto();
            userDTO.setId(Long.valueOf(user.getId()));
            userDTO.setEmail(user.getEmail());

            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}