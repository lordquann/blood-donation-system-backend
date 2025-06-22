package com.example.blood_donation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blood_component")
public class BloodComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer componentId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}

