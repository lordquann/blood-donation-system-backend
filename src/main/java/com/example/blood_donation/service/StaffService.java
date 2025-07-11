package com.example.blood_donation.service;

import com.example.blood_donation.dto.LoginResponse;
import com.example.blood_donation.entity.Staff;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StaffService(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(String email, String rawPassword) {
        Optional<Staff> staffOpt = staffRepository.findByEmail(email);
        if (staffOpt.isEmpty()) return null;

        Staff staff = staffOpt.get();
        if (!passwordEncoder.matches(rawPassword, staff.getPassword())) return null;

        String token = UUID.randomUUID().toString(); // hoặc JWT
        return new LoginResponse(token, "Staff", null, staff, null);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Integer id, Staff updatedStaff) {
        Staff staff = getStaffById(id);
        if (updatedStaff.getFullName() != null && !updatedStaff.getFullName().isBlank()) {
            staff.setFullName(updatedStaff.getFullName());
        }
        if (updatedStaff.getUsername() != null && !updatedStaff.getUsername().isBlank()) {
            staff.setUsername(updatedStaff.getUsername());
        }
        if (updatedStaff.getPassword() != null && !updatedStaff.getPassword().isBlank()) {
            staff.setPassword(updatedStaff.getPassword());
        }
        if (updatedStaff.getEmail() != null && !updatedStaff.getEmail().isBlank()) {
            staff.setEmail(updatedStaff.getEmail());
        }
        if (updatedStaff.getPhone() != null && !updatedStaff.getPhone().isBlank()) {
            staff.setPhone(updatedStaff.getPhone());
        }
        if (updatedStaff.getRole() != null) {
            staff.setRole(updatedStaff.getRole());
        }
        return staffRepository.save(staff);
    }

    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }
}
