package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.LiquidityTransactionRepository;
import com.gambasoftware.crypto_monitor.repository.models.LiquidityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiquidityTransactionService {

    @Autowired
    private LiquidityTransactionRepository repository;

    public LiquidityTransaction createTransaction(LiquidityTransaction transaction) {
        return repository.save(transaction);
    }

    public Optional<LiquidityTransaction> deactivateTransaction(Long id) {
        Optional<LiquidityTransaction> transaction = repository.findById(id);
        transaction.ifPresent(t -> {
            t.setIsActive("false");
            repository.save(t);
        });
        return transaction;
    }

    public List<LiquidityTransaction> getByUserIdAndIsActive(String userId, String isActive) {
        return repository.findByUserIdAndIsActive(userId, isActive);
    }

    public Optional<LiquidityTransaction> getById(Long id) {
        return repository.findById(id);
    }

    public List<LiquidityTransaction> getAll() {
        return repository.findAll();
    }
}
