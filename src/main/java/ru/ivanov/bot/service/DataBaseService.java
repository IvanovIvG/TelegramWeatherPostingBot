package ru.ivanov.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.repositories.WeatherRepository;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
public class DataBaseService {
    private final WeatherRepository weatherRepository;

    public void saveWeather(Weather weather){
        weatherRepository.save(weather);
    }

}
