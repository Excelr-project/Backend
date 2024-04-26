package com.example.Backend.repository;

import com.example.Backend.Entity.City;

import java.util.Optional;

public interface CarCityRepository {
    Optional<City> findByName(String cityName);
}
