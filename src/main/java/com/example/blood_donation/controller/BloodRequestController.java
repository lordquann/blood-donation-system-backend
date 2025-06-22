package com.example.blood_donation.controller;

import com.example.blood_donation.entity.BloodRequest;
import com.example.blood_donation.service.BloodRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-request")
@RequiredArgsConstructor
public class BloodRequestController {

    private final BloodRequestService bloodRequestService;

    @GetMapping
    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestService.getAllBloodRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequest> getBloodRequestById(@PathVariable Integer id) {
        return ResponseEntity.ok(bloodRequestService.getBloodRequestById(id));
    }

    @PostMapping
    public ResponseEntity<BloodRequest> createBloodRequest(@RequestBody BloodRequest bloodRequest) {
        return ResponseEntity.ok(bloodRequestService.createBloodRequest(bloodRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodRequest> updateBloodRequest(@PathVariable Integer id, @RequestBody BloodRequest bloodRequest) {
        return ResponseEntity.ok(bloodRequestService.updateBloodRequest(id, bloodRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodRequest(@PathVariable Integer id) {
        bloodRequestService.deleteBloodRequest(id);
        return ResponseEntity.noContent().build();
    }
}
