package com.example.Backend.controller;

import com.example.Backend.Entity.Car;
import com.example.Backend.service.CarCityService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/getByCity")
public class CarCityController {

    private final CarCityService carCityService;

    @GetMapping
    public ResponseEntity<List<Car>> getCarsByCity(@RequestParam String cityName){
        List<Car> cars = carCityService.getCarByCity(cityName);

        if (!cars.isEmpty()){
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
