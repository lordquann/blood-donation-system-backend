package com.example.blood_donation.service;

import com.example.blood_donation.entity.BloodComponent;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.BloodComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodComponentService {

    private final BloodComponentRepository bloodComponentRepository;

    public List<BloodComponent> getAllBloodComponents() {
        return bloodComponentRepository.findAll();
    }

    public BloodComponent getBloodComponentById(Integer id) {
        return bloodComponentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloodComponent not found with id " + id));
    }

    public BloodComponent createBloodComponent(BloodComponent bloodComponent) {
        return bloodComponentRepository.save(bloodComponent);
    }

    public BloodComponent updateBloodComponent(Integer id, BloodComponent updatedBloodComponent) {
        BloodComponent bloodComponent = getBloodComponentById(id);
        if (updatedBloodComponent.getName() != null && !updatedBloodComponent.getName().isBlank()) {
            bloodComponent.setName(updatedBloodComponent.getName());
        }
        if (updatedBloodComponent.getDescription() != null && !updatedBloodComponent.getDescription().isBlank()) {
            bloodComponent.setDescription(updatedBloodComponent.getDescription());
        }
        return bloodComponentRepository.save(bloodComponent);
    }

    public void deleteBloodComponent(Integer id) {
        bloodComponentRepository.deleteById(id);
    }
}

