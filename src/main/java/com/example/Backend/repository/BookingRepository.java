package com.example.Backend.repository;

import com.example.Backend.Entity.Booking;
import com.example.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
 public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserId(Integer userId);

    Optional<Booking> findById(Integer bookingid);

    List<Booking> findByCancelledFalse();


//    @Query("SELECT b FROM Booking b WHERE b.id= :id " + "AND ((b.fromDate >= :fromDate AND b.fromDate <= :toDate) " + "OR (b.toDate >= :fromDate AND b.toDate <= :toDate))")
//    List<Booking> findOverlappingBookings(@Param("id") Integer id, @Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);
}
