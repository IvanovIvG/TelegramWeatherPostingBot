package ru.ivanov.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import ru.ivanov.bot.telegram.Bot;

@SpringBootApplication
public class TelegramWeatherPostingBotApplication {
//    private Bot bot;
//    @Value("${bot.token}")
//    private String botToken;
//
//    @PostConstruct
//    public void registerBot(){
//        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
//            botsApplication.registerBot(botToken, bot);
//            Thread.currentThread().join();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("bot registered");
//    }


    public static void main(String[] args) {
        SpringApplication.run(TelegramWeatherPostingBotApplication.class, args);
    }

}
