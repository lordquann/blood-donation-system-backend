package com.example.blood_donation.controller;

import com.example.blood_donation.dto.LoginRequest;
import com.example.blood_donation.dto.LoginResponse;
import com.example.blood_donation.dto.RegisterRequest;
import com.example.blood_donation.entity.Member;
import com.example.blood_donation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        return ResponseEntity.ok(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        try {
            Member updatedMember = memberService.updateMember(id, member);
            return ResponseEntity.ok(updatedMember);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request.getEmail(), request.getPassword());
        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String result = memberService.register(request);

        if (result.equals("User registered successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}

