package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.CryptoHoldingsRepository;
import com.gambasoftware.crypto_monitor.repository.CryptoPriceRepository;
import com.gambasoftware.crypto_monitor.repository.models.CryptoHoldings;
import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoHoldingsService {

    @Autowired
    private CryptoHoldingsRepository cryptoHoldingsRepository;

    @Autowired
    private CryptoPriceRepository cryptoPriceRepository;

    public CryptoHoldings addCryptoHolding(CryptoHoldings cryptoHoldings) {
        return cryptoHoldingsRepository.save(cryptoHoldings);
    }

    public List<CryptoHoldings> getAllCryptoHoldings() {
        return cryptoHoldingsRepository.findAll();
    }

    public String calculateGainLoss(String symbol) {
        CryptoHoldings holding = cryptoHoldingsRepository.findBySymbol(symbol)
                .orElseThrow(() -> new IllegalArgumentException("Invalid holding id"));

        Optional<CryptoPrice> latestPrice = cryptoPriceRepository
                .findFirstBySymbolOrderByTimestampDesc(holding.getSymbol());

        if (latestPrice.isPresent()) {
            BigDecimal currentPrice = latestPrice.get().getPrice();
            BigDecimal purchasePrice = holding.getPurchasePrice();

            BigDecimal priceDifference = currentPrice.subtract(purchasePrice);
            BigDecimal gainLossPercentage = priceDifference.divide(purchasePrice, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100));

            return gainLossPercentage.setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
        } else {
            throw new RuntimeException("Could not find current price for " + holding.getCryptoName());
        }
    }
}