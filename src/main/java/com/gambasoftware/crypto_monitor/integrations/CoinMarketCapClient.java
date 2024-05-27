package com.gambasoftware.crypto_monitor.integrations;

import com.gambasoftware.crypto_monitor.integrations.models.CoinMarketCapResponse;
import com.gambasoftware.crypto_monitor.integrations.models.CryptoDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinMarketCapClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinMarketCapClient.class);
    @Autowired
    private RestTemplate restTemplate;

    private final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private final String API_KEY = "1cd4a0da-128c-4a6c-bac2-3a61717bb7f9";

    public List<CryptoDataDto> getLatestCryptoData(int limit) {
        LOGGER.info("Calling coinMarketCap API");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("limit", limit);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CoinMarketCapResponse> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                CoinMarketCapResponse.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            CoinMarketCapResponse responseBody = responseEntity.getBody();
            if (responseBody != null && responseBody.getData() != null) {
                return responseBody.getData().stream()
                        .map(this::mapToCryptoDto)
                        .collect(Collectors.toList());
            }
        }

        throw new RuntimeException("Failed to fetch data from CoinMarketCap API");
    }

    private CryptoDataDto mapToCryptoDto(CryptoDataDto dataDto) {
        CryptoDataDto mappedDto = new CryptoDataDto();
        mappedDto.setName(dataDto.getName());
        mappedDto.setSymbol(dataDto.getSymbol());

        if (dataDto.getQuote() != null && dataDto.getQuote().getUsd() != null) {
            CryptoDataDto.UsdDto usdDto = new CryptoDataDto.UsdDto();
            usdDto.setPrice(dataDto.getQuote().getUsd().getPrice());
            mappedDto.setQuote(new CryptoDataDto.QuoteDto());
            mappedDto.getQuote().setUsd(usdDto);
        }

        return mappedDto;
    }
}