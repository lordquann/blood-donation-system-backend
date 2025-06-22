package com.example.blood_donation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String city;

    private BigDecimal latitude;

    private BigDecimal longitude;
}

