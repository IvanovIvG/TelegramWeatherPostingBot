package ru.ivanov.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.bot.model.Weather;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
public class WeatherService {
    private final DataBaseService dataBaseService;
    private final YandexWeatherService yandexWeatherService;

    public Weather getWeatherFromYandexAndSaveInDatabase() {
        Weather currentWeather = yandexWeatherService.getCurrentWeather();
        dataBaseService.saveWeather(currentWeather);
        return currentWeather;
    }
}
