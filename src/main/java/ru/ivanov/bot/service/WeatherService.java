package ru.ivanov.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public Weather getWeatherFromYandexAndSaveInDatabase() throws JsonProcessingException {
        Weather currentWeather = yandexWeatherService.getCurrentWeather();
        dataBaseService.saveWeather(currentWeather);
        return currentWeather;
    }
}
