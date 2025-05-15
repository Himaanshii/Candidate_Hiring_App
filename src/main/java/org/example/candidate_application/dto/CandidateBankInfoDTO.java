package org.example.candidate_application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateBankInfoDTO {
    @NotBlank(message = "Bank name is required")
    private String bankName;

    @Pattern(regexp = "\\d{9,18}", message = "Account number must be between 9 to 18 digits")
    private String accountNumber;

    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code")
    private String ifscCode;
}
