package com.example.blood_donation.service;

import com.example.blood_donation.dto.MemberResponse;
import com.example.blood_donation.entity.Member;
import com.example.blood_donation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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

