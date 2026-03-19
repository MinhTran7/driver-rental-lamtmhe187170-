package com.example.driverrental.controller;

import com.example.driverrental.dto.LoginRequest;
import com.example.driverrental.dto.RegisterRequest;
import com.example.driverrental.dto.ResendVerificationRequest;
import com.example.driverrental.entity.User;
import com.example.driverrental.service.AuthService;
import org.springframework.web.bind.annotation.*;
import com.example.driverrental.entity.Notification;
import java.util.List;

//lamtmhe187170
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/resend-verification")
    public String resendVerification(@RequestBody ResendVerificationRequest request) {
        return authService.resendVerificationEmail(request);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String token) {
        return authService.verifyRegistration(token);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email,
                                 @RequestParam String newPassword) {
        return authService.forgotPassword(email, newPassword);
    }
    @GetMapping("/profile")
    public User getProfile(@RequestParam String email) {
        return authService.getProfile(email);
    }
    @PutMapping("/profile")
    public String updateProfile(@RequestBody User user) {
        return authService.updateProfile(user);
    }

    @GetMapping("/notifications")
    public List<Notification> getNotifications(@RequestParam String email) {
        return authService.getNotifications(email);
    }

    @GetMapping("/test-notification")
    public String testNotification(@RequestParam String email) {
        return authService.createTestNotification(email);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword
    ) {
        return authService.forgotPassword(email, newPassword, confirmPassword);
    }

}