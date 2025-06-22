package com.example.blood_donation.service;

import com.example.blood_donation.entity.Admin;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + id));
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Integer id, Admin updatedAdmin) {
        Admin admin = getAdminById(id);
        admin.setUsername(updatedAdmin.getUsername());
        admin.setPassword(updatedAdmin.getPassword());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setRole(updatedAdmin.getRole());
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }
}
