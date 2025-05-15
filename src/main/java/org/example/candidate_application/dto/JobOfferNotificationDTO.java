package org.example.candidate_application.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferNotificationDTO {
    private Long candidateId;
    private String candidateEmail;
    private String candidateName;
    private String position; // optional
}

