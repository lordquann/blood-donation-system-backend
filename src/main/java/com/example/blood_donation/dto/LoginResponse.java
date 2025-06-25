package com.example.blood_donation.dto;

import com.example.blood_donation.entity.Admin;
import com.example.blood_donation.entity.Member;
import com.example.blood_donation.entity.Staff;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private String token;
    private String role; // "Member", "Staff", "Manager"
    private Member member;
    private Staff staff;
    private Admin admin;

    public LoginResponse(String token, String role, Member member, Staff staff, Admin admin) {
        this.token = token;
        this.role = role;
        this.member = member;
        this.staff = staff;
        this.admin = admin;
    }

    // Getters & setters
}

