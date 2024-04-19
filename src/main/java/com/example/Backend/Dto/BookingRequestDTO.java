package com.example.Backend.Dto;

import com.example.Backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@Component
public class BookingRequestDTO {
    private Integer userId;
    private String fromPlace;
    private String toPlace;
    private Date fromDate;
    private Date toDate;
    private Long car_id;
}
