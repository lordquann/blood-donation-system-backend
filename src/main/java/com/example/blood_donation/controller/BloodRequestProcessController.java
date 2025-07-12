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
    public List<BloodRequestProcess> getAllBloodRequestProcesses() {
        return bloodRequestProcessService.getAllBloodRequestProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequestProcess> getBloodRequestProcessById(@PathVariable Integer id) {
        return ResponseEntity.ok(bloodRequestProcessService.getBloodRequestProcessById(id));
    }

    @PostMapping
    public ResponseEntity<BloodRequestProcess> createBloodRequestProcess(@RequestBody BloodRequestProcess bloodRequestProcess) {
        return ResponseEntity.ok(bloodRequestProcessService.createBloodRequestProcess(bloodRequestProcess));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodRequestProcess> updateBloodRequestProcess(@PathVariable Integer id, @RequestBody BloodRequestProcess bloodRequestProcess) {
        return ResponseEntity.ok(bloodRequestProcessService.updateBloodRequestProcess(id, bloodRequestProcess));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodRequestProcess(@PathVariable Integer id) {
        bloodRequestProcessService.deleteBloodRequestProcess(id);
        return ResponseEntity.noContent().build();
    }
}
