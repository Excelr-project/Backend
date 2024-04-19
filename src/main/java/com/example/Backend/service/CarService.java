package com.example.Backend.service;

import com.example.Backend.Entity.Car;
import com.example.Backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public Car updateCar(Integer id, Car updatedCar){
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()){
            Car existingcar = optionalCar.get();
            existingcar.setCompany(updatedCar.getCompany());
            existingcar.setModel(updatedCar.getModel());
            existingcar.setColor(updatedCar.getColor());
            existingcar.setYear(updatedCar.getYear());
            existingcar.setRentPerDay(updatedCar.getRentPerDay());

            return carRepository.save(existingcar);
        } else {
            throw new RuntimeException("Car not found with id " + id);
        }
    }

    public void deleteCar(Integer id){
        carRepository.deleteById(id);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }


    public Integer getRentPerDay(Integer id){
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getRentPerDay).orElse(null);
    }

}
