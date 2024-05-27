package com.gambasoftware.crypto_monitor.repository;

import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {
    Optional<CryptoPrice> findFirstBySymbolOrderByTimestampDesc(String symbol);
}