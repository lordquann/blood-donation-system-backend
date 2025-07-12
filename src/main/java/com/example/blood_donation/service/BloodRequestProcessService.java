package com.example.blood_donation.service;

import com.example.blood_donation.entity.BloodRequestProcess;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.BloodRequestProcessRepository;
import com.example.blood_donation.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blood_donation.entity.BloodRequestProcess.Status;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestProcessService {

    private final BloodRequestProcessRepository bloodRequestProcessRepository;

    private final EmailService emailService;

    @Autowired
    public BloodRequestProcessService(EmailService emailService, BloodRequestProcessRepository bloodRequestProcessRepository) {
        this.emailService = emailService;
        this.bloodRequestProcessRepository = bloodRequestProcessRepository;
    }

    public List<BloodRequestProcess> getAllBloodRequestProcesses() {
        return bloodRequestProcessRepository.findAll();
    }

    public BloodRequestProcess getBloodRequestProcessById(Integer id) {
        return bloodRequestProcessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloodRequestProcess not found with id " + id));
    }

    public BloodRequestProcess createBloodRequestProcess(BloodRequestProcess bloodRequestProcess) {
        return bloodRequestProcessRepository.save(bloodRequestProcess);
    }

    public BloodRequestProcess updateBloodRequestProcess(Integer id, BloodRequestProcess updatedProcess) {
        BloodRequestProcess process = getBloodRequestProcessById(id);

        if (updatedProcess.getBloodRequest() != null) {
            process.setBloodRequest(updatedProcess.getBloodRequest());
        }
        if (updatedProcess.getMatchedMember() != null) {
            process.setMatchedMember(updatedProcess.getMatchedMember());
        }
        boolean isCompleteNow = false;
        if (updatedProcess.getStatus() != null) {
            process.setStatus(updatedProcess.getStatus());
            if (updatedProcess.getStatus() == Status.Complete) {
                isCompleteNow = true;
            }
        }
        if (updatedProcess.getUpdatedAt() != null) {
            process.setUpdatedAt(updatedProcess.getUpdatedAt());
        }
        BloodRequestProcess saved = bloodRequestProcessRepository.save(process);
        // Gửi email nếu trạng thái là "Complete"
        if (isCompleteNow && process.getBloodRequest() != null) {
            String email = process.getBloodRequest().getMember().getEmail();
            emailService.sendApprovalEmail(
                    email,
                    "Đơn xin máu đã hoàn thành",
                    "Chào bạn,\n\nĐơn xin máu của bạn đã được xử lý và hoàn thành. Vui lòng kiểm tra lại thông tin và liên hệ với bệnh viện nếu cần hỗ trợ thêm.\n\nTrân trọng,\nĐội ngũ Hỗ trợ Hiến Máu"
            );
        }
        return saved;
    }

    public void deleteBloodRequestProcess(Integer id) {
        bloodRequestProcessRepository.deleteById(id);
    }
}
