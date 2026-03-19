package com.example.driverrental.controller;

import com.example.driverrental.entity.Promotion;
import com.example.driverrental.service.PromotionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//lamtmhe187170
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public Promotion create(@RequestBody Promotion promotion) {
        return promotionService.create(promotion);
    }

    @GetMapping
    public List<Promotion> getAll() {
        return promotionService.getAll();
    }

    @PutMapping("/{id}")
    public Promotion update(@PathVariable Long id, @RequestBody Promotion promotion) {
        return promotionService.update(id, promotion);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        promotionService.delete(id);
        return "Deleted promotion with id = " + id;
    }

    @GetMapping("/apply")
    public double applyPromotion(@RequestParam String code, @RequestParam double price) {
        return promotionService.applyPromotion(code, price);
    }


}