package com.example.blood_donation.service;

import com.example.blood_donation.entity.BloodUnit;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.BloodUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodUnitService {

    private final BloodUnitRepository bloodUnitRepository;

    public List<BloodUnit> getAllBloodUnits() {
        return bloodUnitRepository.findAll();
    }

    public BloodUnit getBloodUnitById(Integer id) {
        return bloodUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloodUnit not found with id " + id));
    }

    public BloodUnit createBloodUnit(BloodUnit bloodUnit) {
        return bloodUnitRepository.save(bloodUnit);
    }

    public BloodUnit updateBloodUnit(Integer id, BloodUnit updatedBloodUnit) {
        BloodUnit bloodUnit = getBloodUnitById(id);
        if (updatedBloodUnit.getDonation() != null) {
            bloodUnit.setDonation(updatedBloodUnit.getDonation());
        }
        if (updatedBloodUnit.getComponent() != null) {
            bloodUnit.setComponent(updatedBloodUnit.getComponent());
        }
        if (updatedBloodUnit.getBloodType() != null) {
            bloodUnit.setBloodType(updatedBloodUnit.getBloodType());
        }
        if (updatedBloodUnit.getVolumeMl() != null) {
            bloodUnit.setVolumeMl(updatedBloodUnit.getVolumeMl());
        }
        if (updatedBloodUnit.getLocation() != null) {
            bloodUnit.setLocation(updatedBloodUnit.getLocation());
        }
        if (updatedBloodUnit.getStatus() != null ) {
            bloodUnit.setStatus(updatedBloodUnit.getStatus());
        }
        if (updatedBloodUnit.getCreatedAt() != null) {
            bloodUnit.setCreatedAt(updatedBloodUnit.getCreatedAt());
        }
        return bloodUnitRepository.save(bloodUnit);
    }

    public void deleteBloodUnit(Integer id) {
        bloodUnitRepository.deleteById(id);
    }
}
