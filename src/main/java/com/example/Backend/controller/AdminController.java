package com.example.Backend.controller;

import com.example.Backend.Entity.Booking;
import com.example.Backend.service.AdminService;
import com.example.Backend.service.AdminSigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final AdminService adminService;
    private final AdminSigninService adminSigninService;

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "key", required = true) String key){
        if (adminSigninService.signinAdmin(email, key)){
            return ResponseEntity.ok("Signin Succesful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or key");
        }
    }

    @PostMapping("/generateKey")
    public ResponseEntity<String> generateKey(@RequestParam(value = "email", required = true) String email){
        String key = adminService.generateAndSendKey(email);
        adminSigninService.setAdminKey(key);

        if (key != null){
            return ResponseEntity.ok(key);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate key");
        }
    }


//    @GetMapping("/bookings")
//    public List<Booking> getBookings(){
//        return ad.getUserBookings();
//    }
}
