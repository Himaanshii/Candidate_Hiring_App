package org.example.candidate_application.service;

import org.example.candidate_application.dto.*;
import org.example.candidate_application.entity.OtpVerification;
import org.example.candidate_application.entity.User;
import org.example.candidate_application.repository.OtpVerificationRepository;
import org.example.candidate_application.repository.UserRepository;
import org.example.candidate_application.util.JwtUtil;
import org.example.candidate_application.util.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private OtpVerificationRepository otpRepository;
    @Autowired private EmailService emailService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;


    public String registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        userRepository.save(user);

        String otp = OtpGenerator.generateOtp();
        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setEmail(request.getEmail());
        otpVerification.setOtp(otp);
        otpVerification.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpVerification.setIsUsed(false);
        otpRepository.save(otpVerification);

        emailService.sendOtp(request.getEmail(), otp);

        return "User registered. Please verify OTP sent to email.";
    }

    public String verifyOtp(OtpRequest request) {
        OtpVerification otpRecord = otpRepository.findTopByEmailOrderByExpiryTimeDesc(request.getEmail())
                .orElseThrow(() -> new RuntimeException("OTP not found."));

        if (otpRecord.isUsed() || otpRecord.getExpiryTime().isBefore(LocalDateTime.now())) {
            return "OTP is expired or already used.";
        }

        if (!otpRecord.getOtp().equals(request.getOtp())) {
            return "Invalid OTP.";
        }

        otpRecord.setIsUsed(true);
        otpRepository.save(otpRecord);

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        user.setVerified(true);
        userRepository.save(user);

        return "OTP verified. Account activated.";
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isVerified()) {
            throw new RuntimeException("Account not verified. Please verify OTP.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }



    public String sendResetOtp(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found."));

        String otp = generateOtp();

        OtpVerification otpRecord = new OtpVerification();
        otpRecord.setEmail(request.getEmail());
        otpRecord.setOtp(otp);
        otpRecord.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRecord.setIsUsed(false);
        otpRepository.save(otpRecord);

        emailService.sendOtp(request.getEmail(), otp);

        return "Reset OTP sent to email.";
    }

    public String resetPassword(ResetPasswordRequest request) {
        OtpVerification otpRecord = otpRepository.findTopByEmailOrderByExpiryTimeDesc(request.getEmail())
                .orElseThrow(() -> new RuntimeException("OTP not found."));

        if (otpRecord.isUsed() || otpRecord.getExpiryTime().isBefore(LocalDateTime.now())) {
            return "OTP is expired or already used.";
        }

        if (!otpRecord.getOtp().equals(request.getOtp())) {
            return "Invalid OTP.";
        }

        otpRecord.setIsUsed(true);
        otpRepository.save(otpRecord);

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return "Password reset successful.";
    }

    private String generateOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }
}
