package com.campusconnect.backend.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.backend.entity.EmailOtp;
import com.campusconnect.backend.repository.EmailOtpRepository;

@Service
public class OtpService {

    @Autowired
    private EmailOtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void sendOtp(String email) {

        // Remove existing OTP for this email
        otpRepository.deleteByEmail(email);

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Create new OTP record
        EmailOtp emailOtp = new EmailOtp();
        emailOtp.setEmail(email);
        emailOtp.setOtp(otp);
        emailOtp.setVerified(false);
        emailOtp.setExpiresAt(LocalDateTime.now().plusMinutes(5));

        // Save OTP to database
        otpRepository.save(emailOtp);

        // Send OTP email
        emailService.sendOtp(email, otp);
    }

    @Transactional
    public boolean verifyOtp(String email, String otp) {

        EmailOtp emailOtp = otpRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (emailOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (!emailOtp.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        emailOtp.setVerified(true);
        otpRepository.save(emailOtp);

        return true;
    }

    public boolean isEmailVerified(String email) {

        return otpRepository.findByEmail(email)
                .map(EmailOtp::getVerified)
                .orElse(false);
    }
}