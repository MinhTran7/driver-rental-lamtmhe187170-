package com.example.driverrental.service;

import com.example.driverrental.entity.PricingRule;
import com.example.driverrental.repository.PricingRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//lamtmhe187170
@Service
public class PricingRuleService {

    private final PricingRuleRepository pricingRuleRepository;

    public PricingRuleService(PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public PricingRule create(PricingRule pricingRule) {
        return pricingRuleRepository.save(pricingRule);
    }

    public List<PricingRule> getAll() {
        return pricingRuleRepository.findAll();
    }

    public Optional<PricingRule> findByRuleName(String ruleName) {
        return pricingRuleRepository.findByRuleName(ruleName);
    }

    public PricingRule update(Long id, PricingRule updatedRule) {
        PricingRule rule = pricingRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing rule not found"));

        rule.setRuleName(updatedRule.getRuleName());
        rule.setDescription(updatedRule.getDescription());
        rule.setBasePrice(updatedRule.getBasePrice());
        rule.setPricePerKm(updatedRule.getPricePerKm());
        rule.setPricePerHour(updatedRule.getPricePerHour());
        rule.setActive(updatedRule.isActive());

        return pricingRuleRepository.save(rule);
    }

    public void delete(Long id) {
        pricingRuleRepository.deleteById(id);
    }
}