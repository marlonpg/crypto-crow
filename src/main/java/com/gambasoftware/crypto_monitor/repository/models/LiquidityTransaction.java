package com.gambasoftware.crypto_monitor.repository.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "liquidity_transactions")
public class LiquidityTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "pool_reference", nullable = false)
    private String poolReference;

    @Column(name = "token_a_symbol", nullable = false, length = 20)
    private String tokenASymbol;

    @Column(name = "token_b_symbol", nullable = false, length = 20)
    private String tokenBSymbol;

    @Column(name = "token_a_amount", nullable = false, length = 100)
    private String tokenAAmount;

    @Column(name = "token_b_amount", nullable = false, length = 100)
    private String tokenBAmount;

    @Column(name = "price_lower", nullable = false, length = 100)
    private String priceLower;

    @Column(name = "price_upper", nullable = false, length = 100)
    private String priceUpper;

    @Column(name = "is_active", nullable = false, length = 100)
    private String isActive;

    @Column(name = "position_created_at")
    private LocalDateTime positionCreatedAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPoolReference() {
        return poolReference;
    }

    public void setPoolReference(String poolReference) {
        this.poolReference = poolReference;
    }

    public String getTokenASymbol() {
        return tokenASymbol;
    }

    public void setTokenASymbol(String tokenASymbol) {
        this.tokenASymbol = tokenASymbol;
    }

    public String getTokenBSymbol() {
        return tokenBSymbol;
    }

    public void setTokenBSymbol(String tokenBSymbol) {
        this.tokenBSymbol = tokenBSymbol;
    }

    public String getTokenAAmount() {
        return tokenAAmount;
    }

    public void setTokenAAmount(String tokenAAmount) {
        this.tokenAAmount = tokenAAmount;
    }

    public String getTokenBAmount() {
        return tokenBAmount;
    }

    public void setTokenBAmount(String tokenBAmount) {
        this.tokenBAmount = tokenBAmount;
    }

    public String getPriceLower() {
        return priceLower;
    }

    public void setPriceLower(String priceLower) {
        this.priceLower = priceLower;
    }

    public String getPriceUpper() {
        return priceUpper;
    }

    public void setPriceUpper(String priceUpper) {
        this.priceUpper = priceUpper;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getPositionCreatedAt() {
        return positionCreatedAt;
    }

    public void setPositionCreatedAt(LocalDateTime positionCreatedAt) {
        this.positionCreatedAt = positionCreatedAt;
    }
}
