package com.example.blood_donation.service;

import com.example.blood_donation.entity.DonationRequestProcess;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.DonationRequestProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationRequestProcessService {

    private final DonationRequestProcessRepository donationRequestProcessRepository;

    public List<DonationRequestProcess> getAllDonationRequestProcesses() {
        return donationRequestProcessRepository.findAll();
    }

    public DonationRequestProcess getDonationRequestProcessById(Integer id) {
        return donationRequestProcessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DonationRequestProcess not found with id " + id));
    }

    public DonationRequestProcess createDonationRequestProcess(DonationRequestProcess donationRequestProcess) {
        return donationRequestProcessRepository.save(donationRequestProcess);
    }

    public DonationRequestProcess updateDonationRequestProcess(Integer id, DonationRequestProcess updatedProcess) {
        DonationRequestProcess process = getDonationRequestProcessById(id);
        process.setBloodRequest(updatedProcess.getBloodRequest());
        process.setMatchedMember(updatedProcess.getMatchedMember());
        process.setAssignedUnit(updatedProcess.getAssignedUnit());
        process.setStatus(updatedProcess.getStatus());
        process.setUpdatedAt(updatedProcess.getUpdatedAt());
        return donationRequestProcessRepository.save(process);
    }

    public void deleteDonationRequestProcess(Integer id) {
        donationRequestProcessRepository.deleteById(id);
    }
}
