package com.example.blood_donation.service;

import com.example.blood_donation.entity.Staff;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

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
        staff.setFullName(updatedStaff.getFullName());
        staff.setUsername(updatedStaff.getUsername());
        staff.setPassword(updatedStaff.getPassword());
        staff.setEmail(updatedStaff.getEmail());
        staff.setPhone(updatedStaff.getPhone());
        staff.setRole(updatedStaff.getRole());
        return staffRepository.save(staff);
    }

    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }
}
