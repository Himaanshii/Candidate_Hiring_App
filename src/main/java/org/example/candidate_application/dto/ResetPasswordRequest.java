package org.example.candidate_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "OTP is required")
    @Size(min = 4, max = 6, message = "OTP must be between 4 and 6 characters")
    private String otp;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 20, message = "New password must be between 8 and 20 characters")
    private String newPassword;
}