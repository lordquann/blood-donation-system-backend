package com.example.blood_donation.controller;

import com.example.blood_donation.entity.BloodComponent;
import com.example.blood_donation.service.BloodComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-component")
@RequiredArgsConstructor
public class BloodComponentController {

    private final BloodComponentService bloodComponentService;

    @GetMapping
    public List<BloodComponent> getAllBloodComponents() {
        return bloodComponentService.getAllBloodComponents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodComponent> getBloodComponentById(@PathVariable Integer id) {
        return ResponseEntity.ok(bloodComponentService.getBloodComponentById(id));
    }

    @PostMapping
    public ResponseEntity<BloodComponent> createBloodComponent(@RequestBody BloodComponent bloodComponent) {
        return ResponseEntity.ok(bloodComponentService.createBloodComponent(bloodComponent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodComponent> updateBloodComponent(@PathVariable Integer id, @RequestBody BloodComponent bloodComponent) {
        return ResponseEntity.ok(bloodComponentService.updateBloodComponent(id, bloodComponent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodComponent(@PathVariable Integer id) {
        bloodComponentService.deleteBloodComponent(id);
        return ResponseEntity.noContent().build();
    }
}
