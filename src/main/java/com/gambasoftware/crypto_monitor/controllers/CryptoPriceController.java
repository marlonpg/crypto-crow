package com.gambasoftware.crypto_monitor.controllers;

import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import com.gambasoftware.crypto_monitor.services.CryptoPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cryptos")
public class CryptoPriceController {

    @Autowired
    private CryptoPriceService cryptoPriceService;

    @GetMapping
    public List<CryptoPrice> getAllCryptoPrices() {
        return cryptoPriceService.findAll();
    }

    @PostMapping
    public CryptoPrice createCryptoPrice(@RequestBody CryptoPrice cryptoPrice) {
        return cryptoPriceService.save(cryptoPrice);
    }
}