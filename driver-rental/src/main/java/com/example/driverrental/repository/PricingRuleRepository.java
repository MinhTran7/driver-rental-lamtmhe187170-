package com.example.driverrental.repository;

import com.example.driverrental.entity.PricingRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//lamtmhe187170
public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    Optional<PricingRule> findByRuleName(String ruleName);
}