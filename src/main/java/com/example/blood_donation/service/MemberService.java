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
                    existingMember.setFullName(updatedMember.getFullName());
                    existingMember.setDob(updatedMember.getDob());
                    existingMember.setGender(updatedMember.getGender());
                    existingMember.setBloodType(updatedMember.getBloodType());
                    existingMember.setPhone(updatedMember.getPhone());
                    existingMember.setEmail(updatedMember.getEmail());
                    existingMember.setPassword(updatedMember.getPassword());
                    existingMember.setAddress(updatedMember.getAddress());
                    existingMember.setLatitude(updatedMember.getLatitude());
                    existingMember.setLongitude(updatedMember.getLongitude());
                    existingMember.setLastDonation(updatedMember.getLastDonation());
                    existingMember.setHealthNotes(updatedMember.getHealthNotes());
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

