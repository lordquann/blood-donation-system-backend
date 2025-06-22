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
        donation.setMember(updatedDonation.getMember());
        donation.setLocation(updatedDonation.getLocation());
        donation.setDate(updatedDonation.getDate());
        donation.setVolumeMl(updatedDonation.getVolumeMl());
        donation.setNotes(updatedDonation.getNotes());
        return donationRepository.save(donation);
    }

    public void deleteDonation(Integer id) {
        donationRepository.deleteById(id);
    }
}
