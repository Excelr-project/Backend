package com.example.Backend.service;

import com.example.Backend.Entity.User;
import com.example.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SigninService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public  boolean signinUser(String email, String password){

        Optional<User> optionaluser = userRepository.findByEmail(email);

        if (optionaluser.isPresent()){
            User user = optionaluser.get();

            if (passwordEncoder.matches(password, user.getPassword())){
                return true;
            }
        }
        return false;
    }


    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }
}
