package com.gambasoftware.crypto_monitor;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTelegramBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String chatId;

    public MyTelegramBot(String botToken, String chatId) {
        this.botToken = botToken;
        this.chatId = chatId;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Handle incoming updates (optional)
    }

    @Override
    public String getBotUsername() {
        return "@crypto_crow_the_bot"; // Replace with your bot's username
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Error sending message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

