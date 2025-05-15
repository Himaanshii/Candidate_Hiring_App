package org.example.candidate_application.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private OnboardStatus onboardStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private CandidatePersonalInfo personalInfo;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private CandidateBankInfo bankInfo;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private CandidateEducation education;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CandidateDocument> documents;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<JobOfferNotification> jobOfferNotifications;
}
