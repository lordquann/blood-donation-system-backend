package com.example.blood_donation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullName;
    private LocalDate dob; // ISO format: yyyy-MM-dd
    private String gender; // "Male", "Female", or "Others"
    private String bloodType;
    private String phone;
    private String email;
    private String password;
    private String address;
    private Double latitude;
    private Double longitude;
    private LocalDate lastDonation; // ISO format: yyyy-MM-dd
    private String healthNotes;

}
