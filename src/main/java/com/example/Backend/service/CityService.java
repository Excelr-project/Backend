package com.example.Backend.service;

import com.example.Backend.Entity.City;
import com.example.Backend.repository.CityRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public City saveCity(City city){
        return cityRepository.save(city);
    }
}
