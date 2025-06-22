package com.example.blood_donation.repository;

import com.example.blood_donation.entity.BloodComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodComponentRepository extends JpaRepository<BloodComponent, Integer> {
}

