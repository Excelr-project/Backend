package com.example.Backend.controller;

import com.example.Backend.Dto.BookingDetails;
import com.example.Backend.Dto.BookingRequestDTO;
import com.example.Backend.Entity.Booking;
import com.example.Backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {


    @Autowired
    private final BookingService bookingService;
    @PostMapping("/bookcar")
    public ResponseEntity<String> bookCar(@RequestBody BookingRequestDTO requestDTO){

        Double totalrent = bookingService.bookCar(requestDTO);

        if (totalrent != null){
            return ResponseEntity.status(HttpStatus.OK).body("Booking Succesfull. Total rent :" + totalrent);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking failed");
        }
    }

//    @GetMapping("/cars")
//    public ResponseEntity<searchCar> getCars(@RequestBody searchCar searchcar){
//        List<Car> cars = bookingService.getCars(searchcar);
//    }


    @RequestMapping("/bookings/{userId}")
    public ResponseEntity<List<BookingDetails>> getBookings(@PathVariable Integer userId){
        List<BookingDetails> bookings = bookingService.getUserBookings(userId);

        if (bookings != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        }
         else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/cancel/{bookingid}")
    public ResponseEntity<String> cancelBooking(@PathVariable("bookingid") Integer bookingid){
        boolean cancelled = bookingService.cancelBooking(bookingid);

        if (cancelled){
            List<Booking> activeBookings = bookingService.getActiveBookings();
            return ResponseEntity.ok("Remaining Bookings :" + activeBookings);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}