package com.example.driverrental.repository;

import com.example.driverrental.entity.PendingRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//lamtmhe187170
public interface PendingRegistrationRepository extends JpaRepository<PendingRegistration, Long> {
    Optional<PendingRegistration> findByEmail(String email);
    Optional<PendingRegistration> findByVerifyToken(String verifyToken);
    boolean existsByEmail(String email);
}