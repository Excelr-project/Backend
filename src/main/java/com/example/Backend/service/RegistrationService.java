package com.example.Backend.service;

import com.example.Backend.Entity.User;
import com.example.Backend.Exception.ExistingEntityException;
import com.example.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Integer registerUser(User user) throws ExistingEntityException{

        Optional<User> optionaluser = userRepository.findByEmail(user.getEmail());

        if (optionaluser.isPresent()){
            throw new ExistingEntityException("User with email id already Exists!");
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User saveduser = userRepository.save(user);
            log.info("User with email {} registered succesfully", user.getEmail());
            return saveduser.getId();
        }
    }

    public void updatePassword(Optional<User> user, String newPassword){
//        String encodedPassword = passwordEncoder.encode(newPassword);
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
        if (user.isPresent()){
            User user1 = user.get();
            String encodedPassword = passwordEncoder.encode(newPassword);
            userRepository.save(user1);
        }
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}