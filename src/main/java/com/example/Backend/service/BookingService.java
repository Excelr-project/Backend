package com.example.Backend.service;

import com.example.Backend.Dto.BookingRequestDTO;
import com.example.Backend.Entity.Booking;
import com.example.Backend.Entity.User;
import com.example.Backend.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static lombok.extern.java.Log.*;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SigninService signinService;
    private final CarService carService;
//    private final User user;



    public Double bookCar(BookingRequestDTO requestDTO){

        Optional<User> userOptional = signinService.getUserById(requestDTO.getUserId());

        if (userOptional.isPresent()){
            long noofdays = calculateNumberOfDays(requestDTO.getFromDate(), requestDTO.getToDate());
            double rentPerDay = carService.getRentPerDay(Math.toIntExact(requestDTO.getCar_id()));
            double totalrent = noofdays * rentPerDay;

            Booking booking = new Booking();
            booking.setUser(userOptional.get());
            booking.setFromPlace(requestDTO.getFromPlace());
            booking.setToPlace(requestDTO.getToPlace());
            booking.setFromDate(requestDTO.getFromDate());
            booking.setToDate(requestDTO.getToDate());
            booking.setTotalrent(totalrent);
            bookingRepository.save(booking);

            return totalrent;
        }
        return null;
    }

//    public Optional<Booking> getUserBookings(BookingRequestDTO bookingRequestDTO){
//
//        Integer userId = bookingRequestDTO.getUser();
//        Optional<User> user = signinService.getUserById(userId);
//
//        if (user.isPresent()){
//            return bookingRepository.findById(Long.valueOf(userId));
//        }
//        return null;
//    }


    public List<Booking> getUserBookings(Integer userId){
        Optional<User> userOptional = signinService.getUserById(userId);

        if (userOptional.isPresent()){
            return bookingRepository.findByUserId(userId);

        }

        return Collections.emptyList();
    }

    public long calculateNumberOfDays(Date fromDate, Date toDate){

        return ChronoUnit.DAYS.between(fromDate.toInstant(), toDate.toInstant());
    }


    public boolean cancelBooking(Integer userId){
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        if (!bookings.isEmpty()){
            for (Booking booking : bookings){
                booking.setCancelled(true);
                bookingRepository.save(booking);
            }
            bookingRepository.deleteAll(bookings);
            return true;
        }
        return false;
    }
}
