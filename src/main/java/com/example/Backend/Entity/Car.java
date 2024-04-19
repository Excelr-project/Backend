package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String color;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    public Integer rentPerDay;
}
