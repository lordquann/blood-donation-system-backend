package com.example.blood_donation.service;

import com.example.blood_donation.entity.BloodRequest;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.BloodRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;

    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestRepository.findAll();
    }

    public BloodRequest getBloodRequestById(Integer id) {
        return bloodRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloodRequest not found with id " + id));
    }

    public BloodRequest createBloodRequest(BloodRequest bloodRequest) {
        return bloodRequestRepository.save(bloodRequest);
    }

    public BloodRequest updateBloodRequest(Integer id, BloodRequest updatedBloodRequest) {
        BloodRequest bloodRequest = getBloodRequestById(id);
        if (updatedBloodRequest.getMember() != null) {
            bloodRequest.setMember(updatedBloodRequest.getMember());
        }
        if (updatedBloodRequest.getFullName() != null && !updatedBloodRequest.getFullName().isBlank()) {
            bloodRequest.setFullName(updatedBloodRequest.getFullName());
        }
        if (updatedBloodRequest.getBloodType() != null) {
            bloodRequest.setBloodType(updatedBloodRequest.getBloodType());
        }
        if (updatedBloodRequest.getComponent() != null) {
            bloodRequest.setComponent(updatedBloodRequest.getComponent());
        }
        if (updatedBloodRequest.getHospital() != null && !updatedBloodRequest.getHospital().isBlank()) {
            bloodRequest.setHospital(updatedBloodRequest.getHospital());
        }
        if (updatedBloodRequest.getContact() != null && !updatedBloodRequest.getContact().isBlank()) {
            bloodRequest.setContact(updatedBloodRequest.getContact());
        }
        if (updatedBloodRequest.getLatitude() != null) {
            bloodRequest.setLatitude(updatedBloodRequest.getLatitude());
        }
        if (updatedBloodRequest.getLongitude() != null) {
            bloodRequest.setLongitude(updatedBloodRequest.getLongitude());
        }
        if (updatedBloodRequest.getNeededDate() != null) {
            bloodRequest.setNeededDate(updatedBloodRequest.getNeededDate());
        }
        if (updatedBloodRequest.getIsEmergency() != null) {
            bloodRequest.setIsEmergency(updatedBloodRequest.getIsEmergency());
        }
        if (updatedBloodRequest.getNote() != null && !updatedBloodRequest.getNote().isBlank()) {
            bloodRequest.setNote(updatedBloodRequest.getNote());
        }
        if (updatedBloodRequest.getCreatedAt() != null) {
            bloodRequest.setCreatedAt(updatedBloodRequest.getCreatedAt());
        }
        return bloodRequestRepository.save(bloodRequest);
    }

    public void deleteBloodRequest(Integer id) {
        bloodRequestRepository.deleteById(id);
    }
}
