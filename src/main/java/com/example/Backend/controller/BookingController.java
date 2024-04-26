package com.example.Backend.controller;

import com.example.Backend.Dto.BookingDetails;
import com.example.Backend.Dto.BookingRequestDTO;
import com.example.Backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;
    private final BookingRequestDTO bookingRequestDTO;

    @PostMapping("/bookcar")
    public ResponseEntity<String> bookCar(@RequestBody BookingRequestDTO requestDTO){

        Double totalrent = bookingService.bookCar(requestDTO);

        if (totalrent != null){
            return ResponseEntity.ok("Booking Succesfull. Total rent :" + totalrent);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking failed");
        }
    }


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


    @DeleteMapping("/cancel/{userId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer userId){
//        Integer userId = user.id;
        boolean cancelled = bookingService.cancelBooking(userId);

        if (cancelled){
            return ResponseEntity.ok("Booking Cancelled for" + userId);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
