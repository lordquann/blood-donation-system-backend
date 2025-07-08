package com.example.blood_donation.service;

import com.example.blood_donation.entity.BloodRequestProcess;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.BloodRequestProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestProcessService {

    private final BloodRequestProcessRepository bloodRequestProcessRepository;

    public List<BloodRequestProcess> getAllDonationRequestProcesses() {
        return bloodRequestProcessRepository.findAll();
    }

    public BloodRequestProcess getDonationRequestProcessById(Integer id) {
        return bloodRequestProcessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloodRequestProcess not found with id " + id));
    }

    public BloodRequestProcess createDonationRequestProcess(BloodRequestProcess bloodRequestProcess) {
        return bloodRequestProcessRepository.save(bloodRequestProcess);
    }

    public BloodRequestProcess updateDonationRequestProcess(Integer id, BloodRequestProcess updatedProcess) {
        BloodRequestProcess process = getDonationRequestProcessById(id);
        if (updatedProcess.getBloodRequest() != null) {
            process.setBloodRequest(updatedProcess.getBloodRequest());
        }
        if (updatedProcess.getMatchedMember() != null) {
            process.setMatchedMember(updatedProcess.getMatchedMember());
        }
        if (updatedProcess.getAssignedUnit() != null) {
            process.setAssignedUnit(updatedProcess.getAssignedUnit());
        }
        if (updatedProcess.getStatus() != null) {
            process.setStatus(updatedProcess.getStatus());
        }
        if (updatedProcess.getUpdatedAt() != null) {
            process.setUpdatedAt(updatedProcess.getUpdatedAt());
        }
        return bloodRequestProcessRepository.save(process);
    }

    public void deleteDonationRequestProcess(Integer id) {
        bloodRequestProcessRepository.deleteById(id);
    }
}
