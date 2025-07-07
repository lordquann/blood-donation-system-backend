package com.example.blood_donation.service;

import com.example.blood_donation.entity.Donation;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Integer id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donation not found with id " + id));
    }

    public Donation createDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation updateDonation(Integer id, Donation updatedDonation) {
        Donation donation = getDonationById(id);
        if (updatedDonation.getMember() != null) {
            donation.setMember(updatedDonation.getMember());
        }
        if (updatedDonation.getLocation() != null) {
            donation.setLocation(updatedDonation.getLocation());
        }
        if (updatedDonation.getDate() != null) {
            donation.setDate(updatedDonation.getDate());
        }
        if (updatedDonation.getNotes() != null && !updatedDonation.getNotes().isBlank()) {
            donation.setNotes(updatedDonation.getNotes());
        }
        if (updatedDonation.getStatus() != null) {
            donation.setStatus(updatedDonation.getStatus());
        }
        return donationRepository.save(donation);
    }

    public void deleteDonation(Integer id) {
        donationRepository.deleteById(id);
    }
}
