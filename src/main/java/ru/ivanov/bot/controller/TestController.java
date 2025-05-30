package ru.ivanov.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.service.WeatherService;

/**
 * @author Ivan Ivanov
 **/
@RestController
@RequiredArgsConstructor
public class TestController {
    private final WeatherService weatherService;
    @GetMapping("/weather")
    public Weather getWeather(){
        return weatherService.getWeatherAndSaveInDatabase();
    }

}
