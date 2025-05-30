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
    private final WebService webService;

    public Weather getWeatherAndSaveInDatabase() {
        Weather currentWeather = webService.getCurrentWeather();
        dataBaseService.saveWeather(currentWeather);
        return currentWeather;
    }

    public Weather getWeather(){
        return webService.getCurrentWeather();
    }
}
