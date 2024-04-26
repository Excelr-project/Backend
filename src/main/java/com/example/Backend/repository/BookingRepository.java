package com.example.Backend.repository;

import com.example.Backend.Entity.Booking;
import com.example.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Integer userId);

    Optional<User> getUserById(Integer userId);
}
