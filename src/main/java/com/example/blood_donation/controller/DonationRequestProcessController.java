package com.example.blood_donation.controller;

import com.example.blood_donation.entity.DonationRequestProcess;
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
    public List<DonationRequestProcess> getAllDonationRequestProcesses() {
        return donationRequestProcessService.getAllDonationRequestProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationRequestProcess> getDonationRequestProcessById(@PathVariable Integer id) {
        return ResponseEntity.ok(donationRequestProcessService.getDonationRequestProcessById(id));
    }

    @PostMapping
    public ResponseEntity<DonationRequestProcess> createDonationRequestProcess(@RequestBody DonationRequestProcess donationRequestProcess) {
        return ResponseEntity.ok(donationRequestProcessService.createDonationRequestProcess(donationRequestProcess));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationRequestProcess> updateDonationRequestProcess(@PathVariable Integer id, @RequestBody DonationRequestProcess donationRequestProcess) {
        return ResponseEntity.ok(donationRequestProcessService.updateDonationRequestProcess(id, donationRequestProcess));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationRequestProcess(@PathVariable Integer id) {
        donationRequestProcessService.deleteDonationRequestProcess(id);
        return ResponseEntity.noContent().build();
    }
}
