package com.example.blood_donation.service;

import com.example.blood_donation.entity.Donation;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    private final EmailService emailService;

    @Autowired
    public DonationService(EmailService emailService, DonationRepository donationRepository) {
        this.emailService = emailService;
        this.donationRepository = donationRepository;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Integer id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donation not found with id " + id));
    }

    public Donation createDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation updateDonation(Integer id, Donation updatedDonation) {
        Donation donation = getDonationById(id);

        if (updatedDonation.getMember() != null) {
            donation.setMember(updatedDonation.getMember());
        }
        if (updatedDonation.getLocation() != null) {
            donation.setLocation(updatedDonation.getLocation());
        }
        if (updatedDonation.getDate() != null) {
            donation.setDate(updatedDonation.getDate());
        }
        if (updatedDonation.getNotes() != null && !updatedDonation.getNotes().isBlank()) {
            donation.setNotes(updatedDonation.getNotes());
        }
        // Kiểm tra nếu status là Approved
        boolean isApprovedNow = false;
        if (updatedDonation.getStatus() != null) {
            donation.setStatus(updatedDonation.getStatus());
            if ("Approved".equalsIgnoreCase(updatedDonation.getStatus())) {
                isApprovedNow = true;
            }
        }
        Donation saved = donationRepository.save(donation);
        // Gửi email sau khi lưu thành công và status là Approved
        if (isApprovedNow) {
            String email = donation.getMember().getEmail();
            emailService.sendApprovalEmail(
                    email,
                    "Lịch hiến máu đã được phê duyệt",
                    "Chào bạn,\n\nLịch hiến máu của bạn đã được phê duyệt. Vui lòng đến đúng địa điểm và thời gian đã đăng ký.\n\nTrân trọng,\nĐội ngũ Hiến Máu"
            );
        }
        return saved;
    }

    public void deleteDonation(Integer id) {
        donationRepository.deleteById(id);
    }
}
