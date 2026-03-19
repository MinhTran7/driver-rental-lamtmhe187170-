package com.example.driverrental.service;

import com.example.driverrental.entity.Promotion;
import com.example.driverrental.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


//lamtmhe187170
@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion create(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> findByCode(String code) {
        return promotionRepository.findByCode(code);
    }

    public double applyPromotion(String code, double originalPrice) {
        Optional<Promotion> optionalPromotion = promotionRepository.findByCode(code);

        if (optionalPromotion.isEmpty()) {
            System.out.println("Không tìm thấy mã khuyến mãi");
            return originalPrice;
        }

        Promotion promotion = optionalPromotion.get();
        LocalDate today = LocalDate.now();

        if (!promotion.isActive()) {
            System.out.println("Mã khuyến mãi không hoạt động");
            return originalPrice;
        }

        if (today.isBefore(promotion.getStartDate()) || today.isAfter(promotion.getEndDate())) {
            System.out.println("Mã khuyến mãi đã hết hạn hoặc chưa tới ngày áp dụng");
            return originalPrice;
        }

        return originalPrice - (originalPrice * promotion.getDiscountPercent() / 100);
    }

    public Promotion update(Long id, Promotion updatedPromotion) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        promotion.setCode(updatedPromotion.getCode());
        promotion.setTitle(updatedPromotion.getTitle());
        promotion.setDescription(updatedPromotion.getDescription());
        promotion.setDiscountPercent(updatedPromotion.getDiscountPercent());
        promotion.setStartDate(updatedPromotion.getStartDate());
        promotion.setEndDate(updatedPromotion.getEndDate());
        promotion.setActive(updatedPromotion.isActive());

        return promotionRepository.save(promotion);
    }

    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }
}