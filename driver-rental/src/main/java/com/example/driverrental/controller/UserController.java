package com.example.driverrental.controller;

import com.example.driverrental.dto.UpdateProfileRequest;
import com.example.driverrental.entity.User;
import com.example.driverrental.service.UserService;
import org.springframework.web.bind.annotation.*;


//lamtmhe187170
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/profile")
    public User viewProfile(@PathVariable Long id) {
        return userService.viewProfile(id);
    }

    @PutMapping("/{id}/profile")
    public String updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        return userService.updateProfile(id, request);
    }
}