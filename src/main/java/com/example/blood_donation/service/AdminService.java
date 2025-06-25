package com.example.blood_donation.service;

import com.example.blood_donation.dto.LoginResponse;
import com.example.blood_donation.entity.Admin;
import com.example.blood_donation.exception.ResourceNotFoundException;
import com.example.blood_donation.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(String email, String rawPassword) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isEmpty()) return null;

        Admin admin = adminOpt.get();
        if (!passwordEncoder.matches(rawPassword, admin.getPassword())) return null;

        String token = UUID.randomUUID().toString(); // hoặc JWT
        return new LoginResponse(token, "Manager", null, null, admin);
    }

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
