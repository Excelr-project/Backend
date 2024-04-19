package com.example.Backend.controller;

import com.example.Backend.service.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signin")
public class SigninController {

    private final SigninService signinService;

    @GetMapping()
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {

        if (signinService.signinUser(email, password)) {

            return ResponseEntity.status(HttpStatus.FOUND).body("Sign-in Succesful");
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}







//    public ModelAndView sigininUser(@RequestBody User user){
//        SigninService.signinUser(user);
//
//        String message = SigninService.signinUser(user);
//
//        if (message.equals("Sign-in Successful")){
//            RedirectView redirectView = new RedirectView();
//            redirectView.setUrl("/welcome");  // redirect to welcome page
//            return new ModelAndView(redirectView);
//        } else {
//            ModelAndView maw = new ModelAndView("/signin");
//            maw.addObject("error", "Invalid email or password");
//            return maw;
//        }
//    }

