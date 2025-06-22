package com.example.blood_donation.controller;

import com.example.blood_donation.entity.BloodInventory;
import com.example.blood_donation.service.BloodInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-inventory")
@RequiredArgsConstructor
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    @GetMapping
    public List<BloodInventory> getAllBloodInventories() {
        return bloodInventoryService.getAllBloodInventories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodInventory> getBloodInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(bloodInventoryService.getBloodInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<BloodInventory> createBloodInventory(@RequestBody BloodInventory bloodInventory) {
        return ResponseEntity.ok(bloodInventoryService.createBloodInventory(bloodInventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodInventory> updateBloodInventory(@PathVariable Integer id, @RequestBody BloodInventory bloodInventory) {
        return ResponseEntity.ok(bloodInventoryService.updateBloodInventory(id, bloodInventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodInventory(@PathVariable Integer id) {
        bloodInventoryService.deleteBloodInventory(id);
        return ResponseEntity.noContent().build();
    }
}
