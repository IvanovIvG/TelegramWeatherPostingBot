package ru.ivanov.bot.telegram.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.ivanov.bot.service.SubscribedUserService;

/**
 * @author Ivan Ivanov
 **/
@Component
public class UnsubscribeCommand extends BotCommand {
    private final SubscribedUserService subscribedUserService;

    public UnsubscribeCommand(SubscribedUserService subscribedUserService) {
        super("/unsubscribe", "Отписаться от получения погоды");
        this.subscribedUserService = subscribedUserService;
    }


    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] strings) {
        long userId = user.getId();
        String messageText;
        if(subscribedUserService.userIsNotSubscribed(userId)){
            messageText = """
                Вы не подписаны на рассылку погоды""";
        }
        else{
            subscribedUserService.unsubscribeUser(userId);
            messageText = """
                Вы отписались от рассылки погоды""";
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
