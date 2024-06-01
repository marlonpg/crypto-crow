package com.gambasoftware.crypto_monitor.schedulers;

import com.gambasoftware.crypto_monitor.integrations.clients.CoinMarketCapClient;
import com.gambasoftware.crypto_monitor.integrations.models.CryptoDataDto;
import com.gambasoftware.crypto_monitor.integrations.services.TelegramBotService;
import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import com.gambasoftware.crypto_monitor.repository.models.CryptoTransaction;
import com.gambasoftware.crypto_monitor.services.CryptoPriceService;
import com.gambasoftware.crypto_monitor.services.CryptoTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceScheduler.class);

    @Autowired
    private CryptoPriceService cryptoPriceService;

    @Autowired
    private CoinMarketCapClient coinMarketCapClient;

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private CryptoTransactionService cryptoTransactionService;

    //every minute / 60000 milliseconds
    //@Scheduled(fixedRate = 300000)
    public void recordPrices() {
        LOGGER.info("recordPrices triggered...");
        List<CryptoDataDto> cryptoDataList = coinMarketCapClient.getLatestCryptoData(100);

        for (CryptoDataDto cryptoData : cryptoDataList) {
            CryptoPrice cryptoPrice = new CryptoPrice();
            cryptoPrice.setSymbol(cryptoData.getSymbol());
            cryptoPrice.setPrice(cryptoData.getQuote().getUsd().getPrice().toPlainString());
            cryptoPrice.setTimestamp(LocalDateTime.now());

            cryptoPriceService.save(cryptoPrice);
        }
    }

    //every 10 minutes 600000
    @Scheduled(fixedRate = 150000)
    public void monitorPrices() {
        LOGGER.info("monitorPrices triggered...");
        List<CryptoTransaction> cryptoTransactions = cryptoTransactionService.getAllCryptoTransactions();
        for (CryptoTransaction cryptoTransaction : cryptoTransactions) {
            String gainLossPercent = cryptoTransactionService.calculateGainLoss(cryptoTransaction.getSymbol());
            String message = String.format("The price of %s has changed %s", cryptoTransaction.getSymbol(), gainLossPercent);
            telegramBotService.sendMessageToChannel(message);
        }
//        List<CryptoPrice> latestPrices = cryptoPriceService.findLatestPrices();
//        for (CryptoPrice price : latestPrices) {
//            BigDecimal currentPrice = price.getPrice();
//            // Check if the price has changed significantly (you can define your own logic here)
//            if (currentPrice.compareTo(price.getPreviousPrice()) != 0) {
//                String message = String.format("The price of %s has changed to %s", price.getSymbol(), currentPrice);
//                telegramBotService.sendMessageToChannel(message);
//            }
//        }
    }
}
