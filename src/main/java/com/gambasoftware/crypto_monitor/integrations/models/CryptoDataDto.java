package com.gambasoftware.crypto_monitor.integrations.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CryptoDataDto {
    private String name;
    private String symbol;
    private QuoteDto quote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public QuoteDto getQuote() {
        return quote;
    }

    public void setQuote(QuoteDto quote) {
        this.quote = quote;
    }

    public static class QuoteDto {
        @JsonProperty("USD")
        private UsdDto usd;

        public UsdDto getUsd() {
            return usd;
        }

        public void setUsd(UsdDto usd) {
            this.usd = usd;
        }
    }

    public static class UsdDto {
        private BigDecimal price;

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}