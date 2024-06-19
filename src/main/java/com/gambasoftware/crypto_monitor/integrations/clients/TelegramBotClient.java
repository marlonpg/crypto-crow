package com.gambasoftware.crypto_monitor.integrations.clients;

import com.gambasoftware.crypto_monitor.services.ActionModelConverter;
import com.gambasoftware.crypto_monitor.services.processors.ActionProcessorFactory;
import com.gambasoftware.crypto_monitor.services.models.ActionModel;
import io.micrometer.common.util.StringUtils;
import jakarta.inject.Inject;
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
    @Inject
    private ActionModelConverter actionModelConverter;
    @Inject
    private ActionProcessorFactory actionProcessorFactory;

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
            LOGGER.info("Receiving something from BOT " + messageText);

            ActionModel actionModel = null;
            try {
                actionModel = actionModelConverter.convert(String.valueOf(update.getMessage().getFrom().getId()),
                        update.getMessage().getFrom().getUserName(), messageText);
            } catch (Exception e) {
                LOGGER.error("Failed to convert Received message to ActionModel", e);
            }
            if (actionModel != null) {
                String response = actionProcessorFactory.get(actionModel.getAction()).process(actionModel);
                sendMessageToChannel(actionModel.getUserName(), response);
            }
        }
    }

    public void sendMessageToChannel(String messageText) {
        sendMessageToChannel("", messageText);
    }

    public void sendMessageToChannel(String userName, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(channelId);
        if (StringUtils.isNotBlank(userName))
            messageText += " @" + userName;
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Failed to call Telegram bot", e);
            e.printStackTrace();
        }
    }
}
