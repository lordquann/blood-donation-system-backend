package com.example.blood_donation.controller;

import com.example.blood_donation.entity.BloodUnit;
import com.example.blood_donation.service.BloodUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-unit")
@RequiredArgsConstructor
public class BloodUnitController {

    private final BloodUnitService bloodUnitService;

    @GetMapping
    public List<BloodUnit> getAllBloodUnits() {
        return bloodUnitService.getAllBloodUnits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodUnit> getBloodUnitById(@PathVariable Integer id) {
        return ResponseEntity.ok(bloodUnitService.getBloodUnitById(id));
    }

    @PostMapping
    public ResponseEntity<BloodUnit> createBloodUnit(@RequestBody BloodUnit bloodUnit) {
        return ResponseEntity.ok(bloodUnitService.createBloodUnit(bloodUnit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodUnit> updateBloodUnit(@PathVariable Integer id, @RequestBody BloodUnit bloodUnit) {
        return ResponseEntity.ok(bloodUnitService.updateBloodUnit(id, bloodUnit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodUnit(@PathVariable Integer id) {
        bloodUnitService.deleteBloodUnit(id);
        return ResponseEntity.noContent().build();
    }
}

