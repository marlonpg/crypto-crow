package com.gambasoftware.crypto_monitor.repository;

import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {
}