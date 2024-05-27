package com.gambasoftware.crypto_monitor.controllers;

import com.gambasoftware.crypto_monitor.repository.models.CryptoHoldings;
import com.gambasoftware.crypto_monitor.services.CryptoHoldingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto-holdings")
public class CryptoHoldingsController {

    @Autowired
    private CryptoHoldingsService cryptoHoldingsService;

    @PostMapping("/add")
    public CryptoHoldings addCryptoHolding(@RequestBody CryptoHoldings cryptoHoldings) {
        return cryptoHoldingsService.addCryptoHolding(cryptoHoldings);
    }

    @GetMapping("/all")
    public List<CryptoHoldings> getAllCryptoHoldings() {
        return cryptoHoldingsService.getAllCryptoHoldings();
    }

    @GetMapping("/{symbol}/gain-loss")
    public String calculateGainLoss(@PathVariable String symbol) {
        return cryptoHoldingsService.calculateGainLoss(symbol);
    }
}
