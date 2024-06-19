package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.TransactionRepository;
import com.gambasoftware.crypto_monitor.repository.AssetPriceRepository;
import com.gambasoftware.crypto_monitor.repository.models.Transaction;
import com.gambasoftware.crypto_monitor.repository.models.AssetPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AssetPriceRepository assetPriceRepository;

    public Transaction add(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction delete(Long id){
        Transaction deleted = transactionRepository.getReferenceById(id);
        Transaction object = new Transaction();
        object.setAmount(deleted.getAmount());
        object.setTransactionType(deleted.getTransactionType());
        object.setPrice(deleted.getPrice());
        object.setSymbol(deleted.getSymbol());
        object.setId(deleted.getId());
        transactionRepository.deleteById(id);
        return object;
    }

    public String calculateGainLoss(String symbol) {
        Transaction holding = transactionRepository.findBySymbol(symbol)
                .orElseThrow(() -> new IllegalArgumentException("Invalid holding id"));

        Optional<AssetPrice> latestPrice = assetPriceRepository
                .findFirstBySymbolOrderByTimestampDesc(holding.getSymbol());

        if (latestPrice.isPresent()) {
            BigDecimal currentPrice = new BigDecimal(latestPrice.get().getPrice());
            BigDecimal purchasePrice = new BigDecimal(holding.getPrice());

            BigDecimal priceDifference = currentPrice.subtract(purchasePrice);
            BigDecimal gainLossPercentage = priceDifference.divide(purchasePrice, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100));

            return gainLossPercentage.setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
        } else {
            throw new RuntimeException("Could not find current price for " + holding.getSymbol());
        }
    }
}
