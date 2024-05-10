package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column
    private String from_Place;

    @Column(nullable = false)
    private Timestamp fromDate;

    @Column(nullable = false)
    private Timestamp toDate;

    @Column(nullable = false)
    private double totalrent;

    @Column(nullable = false)
    private boolean cancelled;

    @Column(nullable = false)
    private Integer Carid;
}
