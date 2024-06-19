package com.gambasoftware.crypto_monitor.controllers;

import com.gambasoftware.crypto_monitor.repository.models.Transaction;
import com.gambasoftware.crypto_monitor.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public Transaction add(@RequestBody Transaction transaction) {
        return transactionService.add(transaction);
    }

    @GetMapping()
    public List<Transaction> getAllAssets() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{symbol}/gain-loss")
    public String calculateGainLoss(@PathVariable String symbol) {
        return transactionService.calculateGainLoss(symbol);
    }
}
