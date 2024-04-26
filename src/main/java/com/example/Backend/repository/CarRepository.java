package com.example.Backend.repository;

import com.example.Backend.Entity.Car;
import com.example.Backend.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByCity(City city);
}
