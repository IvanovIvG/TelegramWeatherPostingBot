package ru.ivanov.bot.telegram.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.service.WeatherService;
import ru.ivanov.bot.telegram.MessageBuilder;

/**
 * @author Ivan Ivanov
 **/
@Component
public class WeatherCommand extends BotCommand {
    private final WeatherService weatherService;
    private final MessageBuilder messageBuilder;

    public WeatherCommand(WeatherService weatherService, MessageBuilder messageBuilder) {
        super("/weather", "Получить погоду");
        this.weatherService = weatherService;
        this.messageBuilder = messageBuilder;
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] strings) {
        Weather weather = weatherService.getWeather();
        String messageText = messageBuilder.getWeatherMessage(weather);

        long id = user.getId();
        SendMessage message = SendMessage
                .builder()
                .chatId(id)
                .text(messageText)
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
