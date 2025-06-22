package com.example.blood_donation.controller;

import com.example.blood_donation.entity.Donation;
import com.example.blood_donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Integer id) {
        return ResponseEntity.ok(donationService.getDonationById(id));
    }

    @PostMapping
    public ResponseEntity<Donation> createDonation(@RequestBody Donation donation) {
        return ResponseEntity.ok(donationService.createDonation(donation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Integer id, @RequestBody Donation donation) {
        return ResponseEntity.ok(donationService.updateDonation(id, donation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Integer id) {
        donationService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}
