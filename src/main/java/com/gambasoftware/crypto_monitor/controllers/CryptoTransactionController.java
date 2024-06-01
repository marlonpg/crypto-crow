package com.gambasoftware.crypto_monitor.controllers;

import com.gambasoftware.crypto_monitor.repository.models.CryptoTransaction;
import com.gambasoftware.crypto_monitor.services.CryptoTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto-transactions")
public class CryptoTransactionController {

    @Autowired
    private CryptoTransactionService cryptoTransactionService;

    @PostMapping()
    public CryptoTransaction add(@RequestBody CryptoTransaction cryptoTransaction) {
        return cryptoTransactionService.add(cryptoTransaction);
    }

    @GetMapping("/all")
    public List<CryptoTransaction> getAllCryptoHoldings() {
        return cryptoTransactionService.getAllCryptoTransactions();
    }

    @GetMapping("/{symbol}/gain-loss")
    public String calculateGainLoss(@PathVariable String symbol) {
        return cryptoTransactionService.calculateGainLoss(symbol);
    }
}
