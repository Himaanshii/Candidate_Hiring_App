package org.example.candidate_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_verifications")
@Data
public class OtpVerification {
    @Id
    private String email;

    private String otp;

    private LocalDateTime expiryTime;

    private boolean isUsed;

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}

