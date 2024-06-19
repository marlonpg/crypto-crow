package com.gambasoftware.crypto_monitor.repository;

import com.gambasoftware.crypto_monitor.repository.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findBySymbol(String symbol);
    List<Transaction> findByUserId(String userId);
}