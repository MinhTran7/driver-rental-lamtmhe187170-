package com.example.driverrental.repository;

import com.example.driverrental.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//lamtmhe187170
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByCode(String code);
}