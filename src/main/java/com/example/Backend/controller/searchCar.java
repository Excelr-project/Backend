package com.example.Backend.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@RequiredArgsConstructor
public class searchCar {

    private Integer userId;
    private String place;
    private Date fromDate;
    private Date toDate;
}
