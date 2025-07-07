package com.example.blood_donation.service;

import com.example.blood_donation.dto.LoginResponse;
import com.example.blood_donation.dto.MemberResponse;
import com.example.blood_donation.dto.RegisterRequest;
import com.example.blood_donation.entity.Member;
import com.example.blood_donation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String register(RegisterRequest request) {
        // Kiểm tra email trùng
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // Tạo đối tượng Member mới
        Member member = new Member();
        member.setEmail(request.getEmail());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setBloodType(request.getBloodType());
        member.setGender(Member.Gender.valueOf(request.getGender()));

        if (request.getFullName() != null)
            member.setFullName(request.getFullName());

        if (request.getDob() != null)
            member.setDob(request.getDob());

        if (request.getPhone() != null)
            member.setPhone(request.getPhone());

        if (request.getAddress() != null)
            member.setAddress(request.getAddress());

        if (request.getLatitude() != null)
            member.setLatitude(BigDecimal.valueOf(request.getLatitude()));

        if (request.getLongitude() != null)
            member.setLongitude(BigDecimal.valueOf(request.getLongitude()));

        if (request.getLastDonation() != null)
            member.setLastDonation(request.getLastDonation());

        if (request.getHealthNotes() != null)
            member.setHealthNotes(request.getHealthNotes());

        // Lưu vào DB
        memberRepository.save(member);

        return "User registered successfully";
    }


    public LoginResponse login(String email, String rawPassword) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        if (memberOpt.isEmpty()) return null;

        Member member = memberOpt.get();
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) return null;

        String token = UUID.randomUUID().toString(); // hoặc JWT
        return new LoginResponse(token, "Member", member, null, null);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Integer id) {
        return memberRepository.findById(id);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Integer id, Member updatedMember) {
        return memberRepository.findById(id)
                .map(existingMember -> {
                    if (updatedMember.getFullName() != null && !updatedMember.getFullName().isBlank()) {
                        existingMember.setFullName(updatedMember.getFullName());
                    }
                    if (updatedMember.getDob() != null) {
                        existingMember.setDob(updatedMember.getDob());
                    }
                    if (updatedMember.getGender() != null) {
                        existingMember.setGender(updatedMember.getGender());
                    }
                    if (updatedMember.getBloodType() != null && !updatedMember.getBloodType().isBlank()) {
                        existingMember.setBloodType(updatedMember.getBloodType());
                    }
                    if (updatedMember.getPhone() != null && !updatedMember.getPhone().isBlank()) {
                        existingMember.setPhone(updatedMember.getPhone());
                    }
                    if (updatedMember.getEmail() != null && !updatedMember.getEmail().isBlank()) {
                        existingMember.setEmail(updatedMember.getEmail());
                    }
                    if (updatedMember.getPassword() != null && !updatedMember.getPassword().isBlank()) {
                        existingMember.setPassword(updatedMember.getPassword());
                    }
                    if (updatedMember.getAddress() != null && !updatedMember.getAddress().isBlank()) {
                        existingMember.setAddress(updatedMember.getAddress());
                    }
                    if (updatedMember.getLatitude() != null) {
                        existingMember.setLatitude(updatedMember.getLatitude());
                    }
                    if (updatedMember.getLongitude() != null) {
                        existingMember.setLongitude(updatedMember.getLongitude());
                    }
                    if (updatedMember.getLastDonation() != null) {
                        existingMember.setLastDonation(updatedMember.getLastDonation());
                    }
                    if (updatedMember.getHealthNotes() != null && !updatedMember.getHealthNotes().isBlank()) {
                        existingMember.setHealthNotes(updatedMember.getHealthNotes());
                    }
                    return memberRepository.save(existingMember);
                })
                .orElseThrow(() -> new RuntimeException("Member not found with id " + id));
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    public MemberResponse toResponse(Member member) {
        MemberResponse response = new MemberResponse();
        response.setMemberId(member.getMemberId());
        response.setFullName(member.getFullName());
        response.setDob(member.getDob());
        response.setGender(member.getGender());
        response.setBloodType(member.getBloodType());
        response.setPhone(member.getPhone());
        response.setEmail(member.getEmail());
        response.setAddress(member.getAddress());
        response.setLatitude(member.getLatitude());
        response.setLongitude(member.getLongitude());
        response.setLastDonation(member.getLastDonation());
        response.setHealthNotes(member.getHealthNotes());
        return response;
    }

}

