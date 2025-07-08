package com.example.blood_donation.controller;

import com.example.blood_donation.entity.BloodRequestProcess;
import com.example.blood_donation.service.BloodRequestProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-request-process")
@RequiredArgsConstructor
public class BloodRequestProcessController {

    private final BloodRequestProcessService bloodRequestProcessService;

    @GetMapping
    public List<BloodRequestProcess> getAllDonationRequestProcesses() {
        return bloodRequestProcessService.getAllDonationRequestProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequestProcess> getDonationRequestProcessById(@PathVariable Integer id) {
        return ResponseEntity.ok(bloodRequestProcessService.getDonationRequestProcessById(id));
    }

    @PostMapping
    public ResponseEntity<BloodRequestProcess> createDonationRequestProcess(@RequestBody BloodRequestProcess bloodRequestProcess) {
        return ResponseEntity.ok(bloodRequestProcessService.createDonationRequestProcess(bloodRequestProcess));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodRequestProcess> updateDonationRequestProcess(@PathVariable Integer id, @RequestBody BloodRequestProcess bloodRequestProcess) {
        return ResponseEntity.ok(bloodRequestProcessService.updateDonationRequestProcess(id, bloodRequestProcess));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationRequestProcess(@PathVariable Integer id) {
        bloodRequestProcessService.deleteDonationRequestProcess(id);
        return ResponseEntity.noContent().build();
    }
}
