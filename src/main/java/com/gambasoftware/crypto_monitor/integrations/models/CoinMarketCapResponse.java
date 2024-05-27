package com.gambasoftware.crypto_monitor.integrations.models;

import java.util.List;

public class CoinMarketCapResponse {
    private List<CryptoDataDto> data;

    public List<CryptoDataDto> getData() {
        return data;
    }

    public void setData(List<CryptoDataDto> data) {
        this.data = data;
    }
}