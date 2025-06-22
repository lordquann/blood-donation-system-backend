package com.example.blood_donation.service;

import com.example.blood_donation.entity.BloodInventory;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.BloodInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;

    public List<BloodInventory> getAllBloodInventories() {
        return bloodInventoryRepository.findAll();
    }

    public BloodInventory getBloodInventoryById(Integer id) {
        return bloodInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloodInventory not found with id " + id));
    }

    public BloodInventory createBloodInventory(BloodInventory bloodInventory) {
        return bloodInventoryRepository.save(bloodInventory);
    }

    public BloodInventory updateBloodInventory(Integer id, BloodInventory updatedBloodInventory) {
        BloodInventory bloodInventory = getBloodInventoryById(id);
        bloodInventory.setLocation(updatedBloodInventory.getLocation());
        bloodInventory.setBloodType(updatedBloodInventory.getBloodType());
        bloodInventory.setComponent(updatedBloodInventory.getComponent());
        bloodInventory.setUnitCount(updatedBloodInventory.getUnitCount());
        bloodInventory.setLastUpdated(updatedBloodInventory.getLastUpdated());
        return bloodInventoryRepository.save(bloodInventory);
    }

    public void deleteBloodInventory(Integer id) {
        bloodInventoryRepository.deleteById(id);
    }
}

