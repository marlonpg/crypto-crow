package com.gambasoftware.crypto_monitor.repository;

import com.gambasoftware.crypto_monitor.repository.models.AssetPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AssetPriceRepository extends JpaRepository<AssetPrice, Long> {
    Optional<AssetPrice> findFirstBySymbolOrderByTimestampDesc(String symbol);

    @Query("SELECT DISTINCT t.symbol FROM Transaction t WHERE t.assetType = 'stock'")
    List<String> findDistinctStockSymbols();
}