package com.example.blood_donation.repository;

import com.example.blood_donation.entity.DonationRequestProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRequestProcessRepository extends JpaRepository<DonationRequestProcess, Integer> {
    List<DonationRequestProcess> findByStatus(DonationRequestProcess.Status status);
}

