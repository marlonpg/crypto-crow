package com.gambasoftware.crypto_monitor.schedulers;

import com.gambasoftware.crypto_monitor.integrations.CoinMarketCapClient;
import com.gambasoftware.crypto_monitor.integrations.models.CryptoDataDto;
import com.gambasoftware.crypto_monitor.repository.models.CryptoPrice;
import com.gambasoftware.crypto_monitor.services.CryptoPriceService;
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

    //every minute / 60000 milliseconds
    @Scheduled(fixedRate = 90000)
    public void recordPrices() {
        LOGGER.info("recordPrices triggered...");
        List<CryptoDataDto> cryptoDataList = coinMarketCapClient.getLatestCryptoData(10);

        for (CryptoDataDto cryptoData : cryptoDataList) {
            CryptoPrice cryptoPrice = new CryptoPrice();
            cryptoPrice.setSymbol(cryptoData.getSymbol());
            cryptoPrice.setPrice(cryptoData.getQuote().getUsd().getPrice());
            cryptoPrice.setTimestamp(LocalDateTime.now());

            cryptoPriceService.save(cryptoPrice);
        }
    }
}
