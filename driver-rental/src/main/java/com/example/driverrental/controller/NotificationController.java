package com.example.driverrental.controller;

import com.example.driverrental.entity.Notification;
import com.example.driverrental.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//lamtmhe187170
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getByUser(@PathVariable Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }
}