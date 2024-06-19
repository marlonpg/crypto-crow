package com.gambasoftware.crypto_monitor.controllers;

import com.gambasoftware.crypto_monitor.repository.models.AssetPrice;
import com.gambasoftware.crypto_monitor.services.AssetPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cryptos")
public class AssetPriceController {

    @Autowired
    private AssetPriceService assetPriceService;

    @GetMapping
    public List<AssetPrice> getAllAssetPrices() {
        return assetPriceService.findAll();
    }

    @PostMapping
    public AssetPrice addAssetPrice(@RequestBody AssetPrice assetPrice) {
        return assetPriceService.save(assetPrice);
    }
}