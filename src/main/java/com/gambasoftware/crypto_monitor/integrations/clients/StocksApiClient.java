package com.gambasoftware.crypto_monitor.integrations.clients;

import io.micrometer.common.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class StocksApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(StocksApiClient.class);

    public String getStockPrice(String symbol) {
        // URL for the MGLU3 stock page on Status Invest
        String url = "https://statusinvest.com.br/acoes/mglu3";
        //String url = "https://statusinvest.com.br/fundos-imobiliarios/xpml11";
        // Fetch the document over HTTP
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error("Failed to get stock({}) price", symbol, e);
            throw new RuntimeException(e);
        }

        // Use CSS selectors to find the price element
        Elements priceElement = doc.select(".value"); // Class for the price element
        if (priceElement.isEmpty()) {
            LOGGER.error("Price element not found");
            return null;
        }

        // Extract the text from the element
        String price = Optional.ofNullable(priceElement.first()).map(Element::text).orElse("");
        if (StringUtils.isNotBlank(price)) {
            price = price.replace(",", ".");
        }
        LOGGER.info("Cotação atual do MGLU3: R$" + price);

        return price;
    }

    public static void main(String[] args) {
        try {
            // URL for the MGLU3 stock page on Status Invest
            String url = "https://statusinvest.com.br/acoes/mglu3";
            //String url = "https://statusinvest.com.br/fundos-imobiliarios/xpml11";
            // Fetch the document over HTTP
            Document doc = Jsoup.connect(url).get();

            // Use CSS selectors to find the price element
            Elements priceElement = doc.select(".value"); // Class for the price element
            if (priceElement.isEmpty()) {
                System.out.println("Price element not found");
                return;
            }

            // Extract the text from the element
            String price = priceElement.first().text();
            System.out.println("Cotação atual do MGLU3: R$" + price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}