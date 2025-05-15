package org.example.candidate_application.dto;




import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.candidate_application.entity.OnboardStatus;
import org.example.candidate_application.entity.Status;

@Data
public class CandidateDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?[6-9]\\d{9}$",
            message = "Phone number must be valid and start with 6-9 (optional country code allowed)"
    )
    private String phoneNumber;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Onboard status is required")
    private OnboardStatus onboardStatus;


}

