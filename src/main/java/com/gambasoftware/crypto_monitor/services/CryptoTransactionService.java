package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.CryptoTransactionRepository;
import com.gambasoftware.crypto_monitor.repository.CryptoPriceRepository;
import com.gambasoftware.crypto_monitor.repository.models.CryptoTransaction;
import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoTransactionService {

    @Autowired
    private CryptoTransactionRepository cryptoTransactionRepository;

    @Autowired
    private CryptoPriceRepository cryptoPriceRepository;

    public CryptoTransaction add(CryptoTransaction cryptoTransaction) {
        return cryptoTransactionRepository.save(cryptoTransaction);
    }

    public List<CryptoTransaction> getAllCryptoTransactions() {
        return cryptoTransactionRepository.findAll();
    }

    public CryptoTransaction delete(Long id){
        CryptoTransaction deleted = cryptoTransactionRepository.getReferenceById(id);
        CryptoTransaction object = new CryptoTransaction();
        object.setAmount(deleted.getAmount());
        object.setTransactionType(deleted.getTransactionType());
        object.setPrice(deleted.getPrice());
        object.setCryptoName(deleted.getCryptoName());
        object.setSymbol(deleted.getSymbol());
        object.setId(deleted.getId());
        cryptoTransactionRepository.deleteById(id);
        return object;
    }

    public String calculateGainLoss(String symbol) {
        CryptoTransaction holding = cryptoTransactionRepository.findBySymbol(symbol)
                .orElseThrow(() -> new IllegalArgumentException("Invalid holding id"));

        Optional<CryptoPrice> latestPrice = cryptoPriceRepository
                .findFirstBySymbolOrderByTimestampDesc(holding.getSymbol());

        if (latestPrice.isPresent()) {
            BigDecimal currentPrice = new BigDecimal(latestPrice.get().getPrice());
            BigDecimal purchasePrice = new BigDecimal(holding.getPrice());

            BigDecimal priceDifference = currentPrice.subtract(purchasePrice);
            BigDecimal gainLossPercentage = priceDifference.divide(purchasePrice, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100));

            return gainLossPercentage.setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
        } else {
            throw new RuntimeException("Could not find current price for " + holding.getCryptoName());
        }
    }
}
