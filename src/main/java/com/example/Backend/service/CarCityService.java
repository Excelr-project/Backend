package com.example.Backend.service;

import com.example.Backend.Entity.Car;
import com.example.Backend.Entity.City;
import com.example.Backend.repository.CarCityRepository;
import com.example.Backend.repository.CarRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class CarCityService {

    @Autowired
    private CarCityRepository carCityRepository;

    @Autowired
    private CarRepository carRepository;


    public List<Car> getCarByCity(String cityName){

        Optional<City> cityOptional= carCityRepository.findByName(cityName);

        List<Car> cars = new ArrayList<>();
        if (cityOptional.isPresent()){
            cars = carRepository.findByCity(cityOptional.get());
        }

        return cars;
    }
}
