package com.example.Backend.controller;

import com.example.Backend.Entity.Car;
import com.example.Backend.repository.CarRepository;
import com.example.Backend.service.BookingService;
import com.example.Backend.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    @Autowired
    private final CarService carService;

    @Autowired
    private final BookingService bookingService;

    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car savedCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @PutMapping("/updateCar/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car updatedCar){
        Car updated = carService.updateCar(id, updatedCar);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getallCars")
    public ResponseEntity<List<Car>> getAllCar(){
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

//    @GetMapping("/isAvailable")
//    public ResponseEntity<String> checkCarAvailability(@RequestParam Integer id,
//                                                       @RequestParam(name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromdate,
//                                                       @RequestParam(name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate todate){
//
//        Timestamp fromDate = Timestamp.valueOf(fromdate.atStartOfDay());
//        Timestamp toDate = Timestamp.valueOf(todate.atStartOfDay());
//
//
//        boolean isAvailable = bookingService.checkCarAvailability(id, fromDate, toDate);
//
//        if (isAvailable){
//            return ResponseEntity.status(HttpStatus.OK).body("Available");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Available");
//        }
//    }
}
