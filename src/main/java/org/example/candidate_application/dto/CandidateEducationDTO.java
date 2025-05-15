package org.example.candidate_application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CandidateEducationDTO {
    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Institution name is required")
    private String institution;

    @Min(value = 1950, message = "Year of passing must be after 1950")
    @Max(value = 2100, message = "Year of passing must be a valid year")
    private Integer yearOfPassing;
}
