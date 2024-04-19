package com.example.Backend.controller;

import com.example.Backend.Dto.BookingRequestDTO;
import com.example.Backend.Entity.Booking;
import com.example.Backend.Entity.User;
import com.example.Backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    @GetMapping("/bookings/getUserBookings/{id}")
//    public ResponseEntity<Optional<Booking>> getUserBookings(@RequestBody BookingRequestDTO bookingRequestDTO){
//        Optional<Booking> bookings = bookingService.getUserBookings(bookingRequestDTO);
//
//        if (bookings != null){
//            return ResponseEntity.ok().body(bookings);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @RequestMapping("/bookings/{userId}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable Integer userId){
        List<Booking> bookings = bookingService.getUserBookings(userId);

        if (bookings != null) {
//            List<Booking> book = new ArrayList<>();
//
//            for (Booking booking : bookings) {
//                Booking booking1 = new Booking();
//                booking1 = new Booking();
//                booking1.setUser(booking.getUser());
//                booking1.setFromDate(booking.getFromDate());
//                booking1.setToDate(booking.getToDate());
//                booking1.setFromPlace(booking.getFromPlace());
//                booking1.setToPlace(booking.getToPlace());
//                booking1.setTotalrent(booking.getTotalrent());
//                book.add(booking1);
//            }
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        } else {
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
