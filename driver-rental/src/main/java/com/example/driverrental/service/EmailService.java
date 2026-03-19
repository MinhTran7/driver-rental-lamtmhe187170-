package com.example.driverrental.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


//lamtmhe187170
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String toEmail, String token) {
        String verifyLink = "http://localhost:8080/api/auth/verify?token=" + token;

        try {
            System.out.println("Sending verification email to: " + toEmail);
            System.out.println("Verify link: " + verifyLink);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Verify your account");
            message.setText("Click the link below to verify your account:\n" + verifyLink);

            mailSender.send(message);

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send verification email: " + e.getMessage());
        }
    }

}