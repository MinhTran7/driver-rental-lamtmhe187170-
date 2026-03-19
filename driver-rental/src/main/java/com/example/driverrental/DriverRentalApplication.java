package com.example.driverrental;

import com.example.driverrental.entity.Promotion;
import com.example.driverrental.repository.PromotionRepository;
import com.example.driverrental.service.PromotionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DriverRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverRentalApplication.class, args);
    }


}