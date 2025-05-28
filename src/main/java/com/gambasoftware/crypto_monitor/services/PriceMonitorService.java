package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.integrations.clients.TelegramBotClient;
import com.gambasoftware.crypto_monitor.repository.LiquidityTransactionRepository;
import com.gambasoftware.crypto_monitor.repository.models.LiquidityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceMonitorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceMonitorService.class);

    @Autowired
    private LiquidityTransactionRepository liquidityTransactionRepository;
    @Autowired
    private TelegramBotClient telegramBotClient;

    public void handleNewPrice(String symbol, BigDecimal currentPrice) {
        try {
            List<LiquidityTransaction> transactions = liquidityTransactionRepository
                    .findByTokenASymbolAndIsActive(symbol, "true");

            for (LiquidityTransaction tx : transactions) {
                BigDecimal lower = new BigDecimal(tx.getPriceLower());
                BigDecimal upper = new BigDecimal(tx.getPriceUpper());

                if (currentPrice.compareTo(lower) < 0 || currentPrice.compareTo(upper) > 0) {
                    String message = String.format(
                            "⚠️ Price Alert! ⚠️\n\n" +
                                    "%s is out of your defined range!\n" +
                                    "User: %s\n" +
                                    "Current Price: %.2f\n" +
                                    "Expected Range: %.2f - %.2f\n" +
                                    "Position ID: %d\n\n" +
                                    "_Please review your position settings._",
                            symbol.toUpperCase(),
                            tx.getUserId(),
                            currentPrice,
                            lower,
                            upper,
                            tx.getId()
                    );
                    telegramBotClient.sendMessageToChannel(message);
                    LOGGER.info(message);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to handleNewPrice of symbol={}", symbol, e);
        }

    }

}
