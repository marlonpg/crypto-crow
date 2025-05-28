package com.gambasoftware.crypto_monitor.controllers;

import com.gambasoftware.crypto_monitor.repository.models.LiquidityTransaction;
import com.gambasoftware.crypto_monitor.services.LiquidityTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/liquidity-transactions")
public class LiquidityTransactionController {

    @Autowired
    private LiquidityTransactionService service;

    @PostMapping
    public ResponseEntity<LiquidityTransaction> create(@RequestBody LiquidityTransaction transaction) {
        LiquidityTransaction created = service.createTransaction(transaction);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        Optional<LiquidityTransaction> result = service.deactivateTransaction(id);
        if (result.isPresent()) {
            return ResponseEntity.ok("Transaction deactivated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<LiquidityTransaction>> getByUserAndStatus(
            @RequestParam String userId,
            @RequestParam String isActive) {
        List<LiquidityTransaction> results = service.getByUserIdAndIsActive(userId, isActive);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiquidityTransaction> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<LiquidityTransaction>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
