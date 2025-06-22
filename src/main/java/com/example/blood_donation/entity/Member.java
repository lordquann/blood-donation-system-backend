package com.example.blood_donation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    private String fullName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String bloodType;

    private String phone;

    private String email;

    private String password;

    @Column(columnDefinition = "TEXT")
    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private LocalDate lastDonation;

    @Column(columnDefinition = "TEXT")
    private String healthNotes;

    public enum Gender {
        Male, Female, Others
    }
}

