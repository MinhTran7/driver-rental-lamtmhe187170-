package com.example.driverrental.service;

import com.example.driverrental.dto.ForgotPasswordRequest;
import com.example.driverrental.dto.LoginRequest;
import com.example.driverrental.dto.RegisterRequest;
import com.example.driverrental.dto.ResendVerificationRequest;
import com.example.driverrental.entity.PendingRegistration;
import com.example.driverrental.entity.User;
import com.example.driverrental.enums.RegistrationStatus;
import com.example.driverrental.enums.Role;
import com.example.driverrental.repository.PendingRegistrationRepository;
import com.example.driverrental.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.driverrental.entity.Notification;
import com.example.driverrental.entity.User;
import com.example.driverrental.repository.NotificationRepository;
import java.util.List;

import java.util.UUID;


//lamtmhe187170
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PendingRegistrationRepository pendingRegistrationRepository;
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;


    public AuthService(UserRepository userRepository,
                       PendingRegistrationRepository pendingRegistrationRepository,
                       EmailService emailService,
                       NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.pendingRegistrationRepository = pendingRegistrationRepository;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotifications(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return List.of();
        }

        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public String createTestNotification(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "User not found";
        }

        createNotification(user, "System Notification", "This is a test notification");

        return "Notification created";
    }

    public void createNotification(User user, String title, String message) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setRead(false);
        notification.setUser(user);

        notificationRepository.save(notification);
    }

    public String resendVerificationEmail(ResendVerificationRequest request) {
        PendingRegistration pending = pendingRegistrationRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (pending == null) {
            return "No pending registration found";
        }

        String newToken = java.util.UUID.randomUUID().toString();
        pending.setVerifyToken(newToken);
        pendingRegistrationRepository.save(pending);

        emailService.sendVerificationEmail(request.getEmail(), newToken);

        return "Verification email has been resent";
    }

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists in system";
        }

        if (pendingRegistrationRepository.existsByEmail(request.getEmail())) {
            return "This email is waiting for verification. Please click Resend Verification.";
        }

        if (!request.isAcceptedTerms()) {
            return "You must accept terms and conditions";
        }

        String token = UUID.randomUUID().toString();

        PendingRegistration pending = new PendingRegistration();
        pending.setFullName(request.getFullName());
        pending.setEmail(request.getEmail());
        pending.setPassword(request.getPassword());
        pending.setPhone(request.getPhone());
        pending.setAddress(request.getAddress());
        pending.setAcceptedTerms(true);
        pending.setVerifyToken(token);
        pending.setStatus(RegistrationStatus.PENDING);

        pendingRegistrationRepository.save(pending);

        String verifyLink = "http://localhost:8080/api/auth/verify?token=" + token;
        emailService.sendVerificationEmail(request.getEmail(), token);

        return "Registration pending. Please check your email to verify account";
    }

    public String verifyRegistration(String token) {
        PendingRegistration pending = pendingRegistrationRepository.findByVerifyToken(token).orElse(null);

        if (pending == null) {
            return "Invalid verification token";
        }

        if (pending.getStatus() == RegistrationStatus.VERIFIED) {
            return "This account has already been verified";
        }

        if (userRepository.existsByEmail(pending.getEmail())) {
            return "Email already exists in users";
        }

        User user = new User();
        user.setFullName(pending.getFullName());
        user.setEmail(pending.getEmail());
        user.setPassword(pending.getPassword());
        user.setPhone(pending.getPhone());
        user.setAddress(pending.getAddress());
        user.setRole(Role.CUSTOMER);

        userRepository.save(user);

        pending.setStatus(RegistrationStatus.VERIFIED);
        pendingRegistrationRepository.save(pending);

        createNotification(user, "Đăng ký thành công", "Tài khoản của bạn đã được tạo thành công");
        return "Email verified successfully. Account created";
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return "Account does not exist or has not been verified";
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return "Wrong password";
        }

        createNotification(user, "Đăng nhập thành công", "Bạn vừa đăng nhập vào hệ thống Rendtal Driver");
        return "Login success - role: " + user.getRole();
    }

    public String forgotPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "Email does not exist";
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return "Password changed successfully";
    }
    public User getProfile(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public String updateProfile(User updatedUser) {
        User user = userRepository.findByEmail(updatedUser.getEmail()).orElse(null);

        if (user == null) return "User not found";

        user.setFullName(updatedUser.getFullName());
        user.setPhone(updatedUser.getPhone());
        user.setAddress(updatedUser.getAddress());

        userRepository.save(user);

        return "Update success";
    }

    public String forgotPassword(String email, String newPassword, String confirmPassword) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "Email chưa tồn tại hoặc chưa xác thực";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "Mật khẩu nhập lại không khớp";
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return "Đổi mật khẩu thành công";
    }

}