package com.example.driverrental.service;

import com.example.driverrental.entity.Notification;
import com.example.driverrental.entity.User;
import com.example.driverrental.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


//lamtmhe187170
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void createNotification(User user, String title, String message) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setUser(user);
        notification.setRead(false);

        notificationRepository.save(notification);
    }
}