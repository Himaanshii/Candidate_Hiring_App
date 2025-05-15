package org.example.candidate_application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.candidate_application.entity.Candidate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String degree;
    private String institution;
    private Integer yearOfPassing;
}
