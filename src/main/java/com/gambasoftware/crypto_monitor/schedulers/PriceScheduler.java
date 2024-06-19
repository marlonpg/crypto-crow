package com.gambasoftware.crypto_monitor.schedulers;

import com.gambasoftware.crypto_monitor.integrations.clients.CoinMarketCapClient;
import com.gambasoftware.crypto_monitor.integrations.clients.StocksApiClient;
import com.gambasoftware.crypto_monitor.integrations.models.CryptoDataDto;
import com.gambasoftware.crypto_monitor.integrations.clients.TelegramBotClient;
import com.gambasoftware.crypto_monitor.repository.models.AssetPrice;
import com.gambasoftware.crypto_monitor.repository.models.Transaction;
import com.gambasoftware.crypto_monitor.services.AssetPriceService;
import com.gambasoftware.crypto_monitor.services.TransactionService;
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
    private AssetPriceService assetPriceService;

    @Autowired
    private CoinMarketCapClient coinMarketCapClient;

    @Autowired
    private TelegramBotClient telegramBotClient;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StocksApiClient stocksApiClient;

    //every minute / 60000 milliseconds
    @Scheduled(fixedRate = 300000)
    public void recordCryptoPrices() {
        LOGGER.info("recordCryptoPrices triggered...");
        List<CryptoDataDto> cryptoDataList = coinMarketCapClient.getLatestCryptoData(100);

        for (CryptoDataDto cryptoData : cryptoDataList) {
            AssetPrice assetPrice = new AssetPrice();
            assetPrice.setSymbol(cryptoData.getSymbol());
            assetPrice.setPrice(cryptoData.getQuote().getUsd().getPrice().toPlainString());
            assetPrice.setTimestamp(LocalDateTime.now());

            assetPriceService.save(assetPrice);
        }
        LOGGER.info("recordCryptoPrices finished execution...");
    }

    @Scheduled(fixedRate = 60000)
    public void recordStockPrices() {
        LOGGER.info("recordStockPrices triggered...");
        List<String> stocks = assetPriceService.findAllStocks();

        for (String stock : stocks) {
            String price = stocksApiClient.getStockPrice(stock);
            AssetPrice assetPrice = new AssetPrice();
            assetPrice.setSymbol(stock);
            assetPrice.setPrice(price);
            assetPrice.setTimestamp(LocalDateTime.now());

            assetPriceService.save(assetPrice);
        }
        LOGGER.info("recordStockPrices finished execution...");
    }

    //every 5 minutes 300000
    @Scheduled(fixedRate = 300000)
    public void monitorPrices() {
        LOGGER.info("monitorPrices triggered...");
        List<Transaction> transactions = transactionService.getAllTransactions();
        for (Transaction transaction : transactions) {
            String gainLossPercent = transactionService.calculateGainLoss(transaction.getSymbol());
            String message = String.format("The price of %s has changed %s", transaction.getSymbol(), gainLossPercent);
            telegramBotClient.sendMessageToChannel(message);
        }
        LOGGER.info("monitorPrices finished execution...");
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
