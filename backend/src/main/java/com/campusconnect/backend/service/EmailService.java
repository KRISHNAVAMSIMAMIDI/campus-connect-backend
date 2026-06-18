package com.campusconnect.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTestEmail(String toEmail) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Campus Connect Test");

        message.setText(
                "Congratulations! Your email configuration is working."
        );

        mailSender.send(message);
    }

    public void sendOtp(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Campus Connect OTP Verification");

        message.setText(
                "Hello,\n\n" +
                "Your Campus Connect verification code is: " + otp +
                "\n\nThis OTP will expire in 5 minutes." +
                "\n\nPlease do not share this code with anyone." +
                "\n\nRegards,\nCampus Connect Team"
        );

        mailSender.send(message);
    }
}