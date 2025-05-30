package ru.ivanov.bot.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.extensions.bots.commandbot.CommandLongPollingTelegramBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Component
public class Bot extends CommandLongPollingTelegramBot implements SpringLongPollingBot {
    private final String botToken;

    public Bot(List<BotCommand> botCommands, TelegramClient telegramClient,
               @Value("${bot.username}") String botUsername, @Value("${bot.token}") String botToken) {
        super(telegramClient, true, () -> botUsername);
        this.botToken = botToken;
        registerAll(botCommands.toArray(BotCommand[]::new));
    }


    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = """
                    Ваша команда не распознана""";

            SendMessage messageToChannel = SendMessage
                    .builder()
                    .chatId(update.getMessage().getChatId())
                    .text(messageText)
                    .build();
            try {
                telegramClient.execute(messageToChannel);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
