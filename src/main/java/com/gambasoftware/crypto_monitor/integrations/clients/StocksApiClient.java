package com.gambasoftware.crypto_monitor.integrations.clients;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class StocksApiClient {
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