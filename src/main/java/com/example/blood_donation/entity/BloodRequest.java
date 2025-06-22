package com.example.blood_donation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blood_request")
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    private String fullName;

    private String bloodType;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private BloodComponent component;

    private String hospital;

    private String contact;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private LocalDate neededDate;

    private Boolean isEmergency;

    @Column(columnDefinition = "TEXT")
    private String note;

    private LocalDateTime createdAt;
}

