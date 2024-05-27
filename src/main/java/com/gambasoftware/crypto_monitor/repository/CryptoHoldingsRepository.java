package com.gambasoftware.crypto_monitor.repository;

import com.gambasoftware.crypto_monitor.repository.models.CryptoHoldings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoHoldingsRepository extends JpaRepository<CryptoHoldings, Long> {
    Optional<CryptoHoldings> findBySymbol(String symbol);
}