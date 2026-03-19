package com.example.driverrental.repository;

import com.example.driverrental.entity.Notification;
import com.example.driverrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//lamtmhe187170
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}