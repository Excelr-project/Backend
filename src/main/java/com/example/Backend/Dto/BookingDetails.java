package com.example.Backend.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Component
public class BookingDetails {

    private Integer userId;
    private Integer carId;
    private String Place;
    private Date fromDate;
    private Date toDate;
    private double totalRent;
}
