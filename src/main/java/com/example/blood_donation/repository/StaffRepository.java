package com.example.blood_donation.repository;

import com.example.blood_donation.entity.Member;
import com.example.blood_donation.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByEmail(String email);
}
