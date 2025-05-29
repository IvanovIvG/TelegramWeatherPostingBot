package ru.ivanov.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/**
 * @author Ivan Ivanov
 **/
@Configuration
@EnableScheduling
public class BotConfig {
    @Value("${bot.token}")
    String botToken;

    @Bean
    public TelegramClient configureTelegramClient(){
        return new OkHttpTelegramClient(botToken);
    }
}
