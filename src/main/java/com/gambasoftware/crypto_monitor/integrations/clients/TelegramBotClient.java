package com.gambasoftware.crypto_monitor.integrations.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBotClient extends TelegramLongPollingBot {
    private final static Logger LOGGER = LoggerFactory.getLogger(TelegramBotClient.class);

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.channel.id}")
    private String channelId;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            messageText+=" @"+update.getMessage().getFrom().getUserName();
            LOGGER.info("Receiving something from BOT "+ messageText);
            // Send a response (optional)
            sendMessageToChannel(messageText);
        }
    }

    public void sendMessageToChannel(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(channelId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Failed to call Telegram bot", e);
            e.printStackTrace();
        }
    }
}
