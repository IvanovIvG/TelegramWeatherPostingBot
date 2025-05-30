package ru.ivanov.bot.telegram.commands;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;


/**
 * @author Ivan Ivanov
 **/
@Component
public class StartCommand extends BotCommand {
    public StartCommand() {
        super("/start", "Получить приветственное сообщение");
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] strings) {
        long id = user.getId();
        String messageText = """
                Привет %s!""".formatted(user.getFirstName());

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
