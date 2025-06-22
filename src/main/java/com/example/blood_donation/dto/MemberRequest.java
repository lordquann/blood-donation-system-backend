package com.example.blood_donation.dto;

import com.example.blood_donation.entity.Member.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MemberRequest {

    private String fullName;
    private LocalDate dob;
    private Gender gender;
    private String bloodType;
    private String phone;
    private String email;
    private String password;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDate lastDonation;
    private String healthNotes;
}

