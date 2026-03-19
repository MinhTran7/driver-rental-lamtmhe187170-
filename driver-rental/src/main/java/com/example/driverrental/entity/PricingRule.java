package com.example.driverrental.entity;

import jakarta.persistence.*;


//lamtmhe187170
@Entity
@Table(name = "pricing_rules")
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    private String description;

    @Column(nullable = false)
    private Double basePrice = 0.0;

    @Column(nullable = false)
    private Double pricePerKm = 0.0;

    @Column(nullable = false)
    private Double pricePerHour = 0.0;

    private boolean active = true;

    public PricingRule() {
    }

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getDescription() {
        return description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public Double getPricePerKm() {
        return pricePerKm;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public void setPricePerKm(Double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}