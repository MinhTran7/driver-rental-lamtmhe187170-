package com.example.driverrental.service;

import com.example.driverrental.dto.UpdateProfileRequest;
import com.example.driverrental.entity.User;
import com.example.driverrental.repository.UserRepository;
import org.springframework.stereotype.Service;


//lamtmhe187170
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User viewProfile(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public String updateProfile(Long userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return "User not found";
        }

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        userRepository.save(user);

        return "Profile updated successfully";
    }
}