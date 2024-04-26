package com.example.Backend.controller;


import com.example.Backend.Entity.City;
import com.example.Backend.service.CityService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public List<City> cities(){
        return cityService.getCities();
    }

    @PostMapping("/addCities")
    public City addCity(@RequestBody City city){
        return cityService.saveCity(city);
    }
}
