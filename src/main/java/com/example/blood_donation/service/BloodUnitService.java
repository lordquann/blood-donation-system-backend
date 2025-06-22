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
        bloodUnit.setDonation(updatedBloodUnit.getDonation());
        bloodUnit.setComponent(updatedBloodUnit.getComponent());
        bloodUnit.setBloodType(updatedBloodUnit.getBloodType());
        bloodUnit.setVolumeMl(updatedBloodUnit.getVolumeMl());
        bloodUnit.setLocation(updatedBloodUnit.getLocation());
        bloodUnit.setStatus(updatedBloodUnit.getStatus());
        bloodUnit.setCreatedAt(updatedBloodUnit.getCreatedAt());
        return bloodUnitRepository.save(bloodUnit);
    }

    public void deleteBloodUnit(Integer id) {
        bloodUnitRepository.deleteById(id);
    }
}
