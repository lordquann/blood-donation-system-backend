package com.example.blood_donation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blood_request_process")
public class BloodRequestProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer processId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private BloodRequest bloodRequest;

    @ManyToOne
    @JoinColumn(name = "matched_member_id")
    private Member matchedMember;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime updatedAt;

    public enum Status {
        Processing, Found, Complete, Cancel
    }
}

