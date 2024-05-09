package com.example.Backend.Dto;

import com.example.Backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@Component
public class BookingRequestDTO {
    private Integer userId;
    private String Place;
    private Timestamp fromDate;
    private Timestamp toDate;
    private Integer car_id;
}
