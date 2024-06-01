package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.CryptoPriceRepository;
import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoPriceService {

    @Autowired
    private CryptoPriceRepository cryptoPriceRepository;

    public List<CryptoPrice> findAll() {
        return cryptoPriceRepository.findAll();
    }

    public CryptoPrice save(CryptoPrice cryptoPrice) {
        return cryptoPriceRepository.save(cryptoPrice);
    }

}