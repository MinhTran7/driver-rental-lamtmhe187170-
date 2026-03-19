package com.example.driverrental.controller;

import com.example.driverrental.entity.PricingRule;
import com.example.driverrental.service.PricingRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//lamtmhe187170
@RestController
@RequestMapping("/api/pricing-rules")
public class PricingRuleController {

    private final PricingRuleService pricingRuleService;

    public PricingRuleController(PricingRuleService pricingRuleService) {
        this.pricingRuleService = pricingRuleService;
    }

    @PostMapping
    public PricingRule create(@RequestBody PricingRule pricingRule) {
        return pricingRuleService.create(pricingRule);
    }

    @GetMapping
    public List<PricingRule> getAll() {
        return pricingRuleService.getAll();
    }

    @PutMapping("/{id}")
    public PricingRule update(@PathVariable Long id, @RequestBody PricingRule pricingRule) {
        return pricingRuleService.update(id, pricingRule);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        pricingRuleService.delete(id);
        return "Deleted pricing rule with id = " + id;
    }
}