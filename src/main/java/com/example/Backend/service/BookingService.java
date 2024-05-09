package com.example.Backend.service;

import com.example.Backend.Dto.BookingDetails;
import com.example.Backend.Dto.BookingRequestDTO;
import com.example.Backend.Entity.Booking;
import com.example.Backend.Entity.User;
import com.example.Backend.repository.BookingRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private final BookingRepository bookingRepository;
    private final SigninService signinService;
    private final CarService carService;
    private final JavaMailSender javaMailSender;



    public Double bookCar(BookingRequestDTO requestDTO){

        Optional<User> userOptional = signinService.getUserById(requestDTO.getUserId());

        if (userOptional.isPresent()){
            long noofdays = calculateNumberOfDays(requestDTO.getFromDate(), requestDTO.getToDate());
            double rentPerDay = carService.getRentPerDay(Math.toIntExact(requestDTO.getCar_id()));
            double totalrent = noofdays * rentPerDay;

            Booking booking = new Booking();
            booking.setUser(userOptional.get());
            booking.setPlace(requestDTO.getPlace());
            booking.setFromDate((Timestamp) requestDTO.getFromDate());
            booking.setToDate((Timestamp) requestDTO.getToDate());
            booking.setTotalrent(totalrent);
            booking.setCarid(requestDTO.getCar_id());
            bookingRepository.save(booking);

            sendBookingConfirmation(userOptional.get(), booking);

            return totalrent;
        }
        return null;
    }


    private void sendBookingConfirmation(User user, Booking booking){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setTo(user.getEmail());
            helper.setText("Booking Confirmed !");
            helper.setText("Hello dear" + user.getFirstname() + ".\n\nThanks for booking car on our website, We are happy to inform that your booking has confirmed!\n" + "Find Details below:\n" +
                    "From Place : "+ booking.getPlace() +  "\nFrom Date : " + booking.getFromDate() +
                    "\nTo Date : " + booking.getToDate() + "\n Total Amount :" + booking.getTotalrent());

            javaMailSender.send(message);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }




    public List<BookingDetails> getUserBookings(Integer userId){
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        List<BookingDetails> bookingDetails = new ArrayList<>();
        for (Booking booking :  bookings){
            BookingDetails bookingDetail = new BookingDetails();
            bookingDetail.setId(booking.getId());
            bookingDetail.setUserId(booking.getUser().getId());
            bookingDetail.setCarId(Math.toIntExact(booking.getId()));
            bookingDetail.setFromDate(booking.getFromDate());
            bookingDetail.setToDate(booking.getToDate());
            bookingDetail.setTotalRent(booking.getTotalrent());
            bookingDetails.add(bookingDetail);
        }
        return bookingDetails;
    }

    public long calculateNumberOfDays(Date fromDate, Date toDate){

        return ChronoUnit.DAYS.between(fromDate.toInstant(), toDate.toInstant());
    }


    public boolean cancelBooking(Integer bookingid){
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingid);
        if (optionalBooking.isPresent()){
            Booking booking = optionalBooking.get();
            booking.setCancelled(true);
            bookingRepository.save(booking);
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }

    public List<Booking> getActiveBookings(){
        return bookingRepository.findByCancelledFalse();
    }


//    public boolean checkCarAvailability(Integer id, Timestamp fromDate, Timestamp toDate){
//        List<Booking> overlapbookings = bookingRepository.findOverlappingBookings(id, fromDate, toDate);
//
//        return overlapbookings.isEmpty();
//    }
}
