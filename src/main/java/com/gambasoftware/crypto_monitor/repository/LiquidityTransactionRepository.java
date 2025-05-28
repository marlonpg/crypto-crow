package com.gambasoftware.crypto_monitor.repository;

import com.gambasoftware.crypto_monitor.repository.models.LiquidityTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiquidityTransactionRepository extends JpaRepository<LiquidityTransaction, Long> {
    List<LiquidityTransaction> findByIsActive(String isActive);

    List<LiquidityTransaction> findByUserIdAndIsActive(String userId, String isActive);

    List<LiquidityTransaction> findByTokenASymbolAndIsActive(String tokenASymbol, String isActive);
}
