package com.example.Backend.repository;

import com.example.Backend.Entity.Car;
import com.example.Backend.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarCityRepository extends JpaRepository<City, String> {
    Optional<City> findByName(String cityName);
}
