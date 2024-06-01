package com.gambasoftware.crypto_monitor;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {
        String botToken = "";
        String chatId = "-4276284723"; // Replace with your actual channel chatId

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyTelegramBot(botToken, chatId));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Create an instance of MyTelegramBot
        MyTelegramBot myBot = new MyTelegramBot(botToken, chatId);

        // Call sendMessage method
        myBot.sendMessage("Hello from MyTelegramBot!");
    }
}
