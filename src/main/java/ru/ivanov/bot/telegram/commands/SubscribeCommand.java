package ru.ivanov.bot.telegram.commands;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.ivanov.bot.model.SubscribedUser;
import ru.ivanov.bot.repositories.SubscribedUserRepository;
import ru.ivanov.bot.service.SubscribedUserService;

/**
 * @author Ivan Ivanov
 **/
@Component
public class SubscribeCommand extends BotCommand {
    private final SubscribedUserService subscribedUserService;
    private final ModelMapper modelMapper;

    public SubscribeCommand(SubscribedUserService subscribedUserService, ModelMapper modelMapper) {
        super("/subscribe", "Подписаться на получение погоды");
        this.subscribedUserService = subscribedUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] strings) {
        SubscribedUser userToSubscribe = modelMapper.map(user, SubscribedUser.class);
        long userId = user.getId();
        String messageText = "";
        if(subscribedUserService.userIsNotSubscribed(userId)){
            subscribedUserService.subscribeUser(userToSubscribe);
            messageText = """
                Вы подписались на рассылку погоды""";
        }
        else{
            messageText = """
                Вы уже подписаны на рассылку погоды""";
        }
        SendMessage messageToChannel = SendMessage
                .builder()
                .chatId(userId)
                .text(messageText)
                .build();
        try {
            telegramClient.execute(messageToChannel);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
