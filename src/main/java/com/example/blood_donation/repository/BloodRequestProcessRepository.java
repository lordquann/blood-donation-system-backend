package com.example.blood_donation.repository;

import com.example.blood_donation.entity.BloodRequestProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodRequestProcessRepository extends JpaRepository<BloodRequestProcess, Integer> {
    List<BloodRequestProcess> findByStatus(BloodRequestProcess.Status status);
}

