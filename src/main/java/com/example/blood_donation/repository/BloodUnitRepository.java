package com.example.blood_donation.repository;

import com.example.blood_donation.entity.BloodUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodUnitRepository extends JpaRepository<BloodUnit, Integer> {
    List<BloodUnit> findByStatus(BloodUnit.Status status);
}

