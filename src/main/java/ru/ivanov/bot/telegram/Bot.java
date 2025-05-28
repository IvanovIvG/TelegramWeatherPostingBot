package ru.ivanov.bot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.model.enums.PhenomenaCondition;
import ru.ivanov.bot.model.enums.PrecisionType;
import ru.ivanov.bot.model.enums.Strength;
import ru.ivanov.bot.repositories.WeatherRepository;

/**
 * @author Ivan Ivanov
 **/
@Component
@Transactional
@RequiredArgsConstructor
public class Bot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    @Value("${bot.token}")
    String botToken;
    private final TelegramClient telegramClient;
    private final WeatherRepository repository;
    private final MessageBuilder messageBuilder;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Weather weather = repository.findById(5).orElseThrow();
            String messageText = messageBuilder.getWeatherMessage(weather);
            long chat_id = update.getMessage().getChatId();

            SendMessage messageToUser = SendMessage
                    .builder()
                    .chatId(chat_id)
                    .text(messageText)
                    .build();

            SendMessage messageToChannel = SendMessage
                    .builder()
                    .chatId(-1002618924720L)
                    .text(messageText)
                    .build();

            try {
                telegramClient.execute(messageToUser);
                telegramClient.execute(messageToChannel);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
