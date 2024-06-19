package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.AssetPriceRepository;
import com.gambasoftware.crypto_monitor.repository.models.AssetPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetPriceService {

    @Autowired
    private AssetPriceRepository assetPriceRepository;

    public List<AssetPrice> findAll() {
        return assetPriceRepository.findAll();
    }

    public List<String> findAllStocks() {
        return assetPriceRepository.findDistinctStockSymbols();
    }

    public AssetPrice save(AssetPrice assetPrice) {
        return assetPriceRepository.save(assetPrice);
    }

}