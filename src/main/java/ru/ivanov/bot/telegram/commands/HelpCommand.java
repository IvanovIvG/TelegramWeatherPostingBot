package ru.ivanov.bot.telegram.commands;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/**
 * @author Ivan Ivanov
 **/
@Component
public class HelpCommand extends BotCommand {
    private final ApplicationContext context;

    public HelpCommand(ApplicationContext context) {
        super("help", "Получить все возможные команды бота");
        this.context = context;
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] strings) {
        StringBuilder helpMessageBuilder = new StringBuilder("<b>Help</b>\n");
        helpMessageBuilder.append("Список команд бота:\n\n");

        for (IBotCommand botCommand : context.getBean(ICommandRegistry.class).getRegisteredCommands()) {
            helpMessageBuilder.append(botCommand.toString()).append("\n\n");
        }

        SendMessage helpMessage = new SendMessage(chat.getId().toString(), helpMessageBuilder.toString());
        helpMessage.enableHtml(true);

        try {
            telegramClient.execute(helpMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }
}
