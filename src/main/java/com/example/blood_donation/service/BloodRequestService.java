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
        bloodRequest.setMember(updatedBloodRequest.getMember());
        bloodRequest.setFullName(updatedBloodRequest.getFullName());
        bloodRequest.setBloodType(updatedBloodRequest.getBloodType());
        bloodRequest.setComponent(updatedBloodRequest.getComponent());
        bloodRequest.setHospital(updatedBloodRequest.getHospital());
        bloodRequest.setContact(updatedBloodRequest.getContact());
        bloodRequest.setLatitude(updatedBloodRequest.getLatitude());
        bloodRequest.setLongitude(updatedBloodRequest.getLongitude());
        bloodRequest.setNeededDate(updatedBloodRequest.getNeededDate());
        bloodRequest.setIsEmergency(updatedBloodRequest.getIsEmergency());
        bloodRequest.setNote(updatedBloodRequest.getNote());
        bloodRequest.setCreatedAt(updatedBloodRequest.getCreatedAt());
        return bloodRequestRepository.save(bloodRequest);
    }

    public void deleteBloodRequest(Integer id) {
        bloodRequestRepository.deleteById(id);
    }
}
