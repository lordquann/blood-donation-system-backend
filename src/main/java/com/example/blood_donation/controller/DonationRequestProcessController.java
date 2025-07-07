package com.example.blood_donation.controller;

import com.example.blood_donation.entity.BloodRequestProcess;
import com.example.blood_donation.service.DonationRequestProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation-request-process")
@RequiredArgsConstructor
public class DonationRequestProcessController {

    private final DonationRequestProcessService donationRequestProcessService;

    @GetMapping
    public List<BloodRequestProcess> getAllDonationRequestProcesses() {
        return donationRequestProcessService.getAllDonationRequestProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequestProcess> getDonationRequestProcessById(@PathVariable Integer id) {
        return ResponseEntity.ok(donationRequestProcessService.getDonationRequestProcessById(id));
    }

    @PostMapping
    public ResponseEntity<BloodRequestProcess> createDonationRequestProcess(@RequestBody BloodRequestProcess bloodRequestProcess) {
        return ResponseEntity.ok(donationRequestProcessService.createDonationRequestProcess(bloodRequestProcess));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodRequestProcess> updateDonationRequestProcess(@PathVariable Integer id, @RequestBody BloodRequestProcess bloodRequestProcess) {
        return ResponseEntity.ok(donationRequestProcessService.updateDonationRequestProcess(id, bloodRequestProcess));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationRequestProcess(@PathVariable Integer id) {
        donationRequestProcessService.deleteDonationRequestProcess(id);
        return ResponseEntity.noContent().build();
    }
}
