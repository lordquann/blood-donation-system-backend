package com.example.blood_donation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blood_inventory")
public class BloodInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private String bloodType;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private BloodComponent component;

    private Integer unitCount;

    private LocalDateTime lastUpdated;
}

