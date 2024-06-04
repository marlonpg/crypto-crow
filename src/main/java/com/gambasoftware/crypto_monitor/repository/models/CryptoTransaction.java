package com.gambasoftware.crypto_monitor.repository.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CryptoTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cryptoName;
    private String symbol;
    @Column(nullable = false, scale=100)
    private String price;
    @Column(nullable = false, scale=100)
    private String amount;
    private String transactionType;
    private LocalDateTime purchaseDateTime = LocalDateTime.now();
    private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
