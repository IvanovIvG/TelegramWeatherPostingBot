package ru.ivanov.bot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.ivanov.bot.model.Channel;
import ru.ivanov.bot.model.SubscribedUser;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.repositories.ChannelRepository;
import ru.ivanov.bot.repositories.SubscribedUserRepository;
import ru.ivanov.bot.service.WeatherService;

import java.util.List;

/**
 * Sends weather to subscribed users and channels once an hour
 *
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class ScheduledMessageSender {
    private final WeatherService service;
    private final MessageBuilder messageBuilder;
    private final TelegramClient telegramClient;
    private final SubscribedUserRepository subscribedUserRepository;
    private final ChannelRepository channelRepository;

    @Scheduled(cron = "0 * * * * *")
    public void sendScheduledMessage() {
        Weather weather = service.getWeatherAndSaveInDatabase();
        String messageText = messageBuilder.getWeatherMessage(weather);
        sendMessageToChannels(messageText);
        sendMessageToUsers(messageText);
    }

    private void sendMessageToChannels(String messageText) {
        List<Channel> channelsToSendMessages = channelRepository.findAll();
        for (Channel channel : channelsToSendMessages) {
            long id = channel.getChannelID();
            sendMessageById(id, messageText);
        }
    }

    private void sendMessageToUsers(String messageText) {
        List<SubscribedUser> usersToSendMessages = subscribedUserRepository.findAll();
        for (SubscribedUser user : usersToSendMessages) {
            long id = user.getUserID();
            sendMessageById(id, messageText);
        }
    }

    private void sendMessageById(long id, String messageText) {
        SendMessage messageToChannel = SendMessage
                .builder()
                .chatId(id)
                .text(messageText)
                .build();
        try {
            telegramClient.execute(messageToChannel);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
