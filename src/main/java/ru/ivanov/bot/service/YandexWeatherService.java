package ru.ivanov.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.util.WeatherData;

import java.time.LocalDateTime;

/**
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class YandexWeatherService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public Weather getCurrentWeather() throws JsonProcessingException {
        String url = "https://api.weather.yandex.ru/v2/forecast";
        HttpEntity<Void> requestEntity = createRequest();

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class);

        String weatherJson = response.getBody();
        return parseJson(weatherJson);
    }

    private HttpEntity<Void> createRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Yandex-Weather-Key", "9308ba1f-746a-4e0a-8a70-b8bf59e41453");
        return new HttpEntity<>(headers);
    }

    private Weather parseJson(String weatherJson) throws JsonProcessingException {
        JsonNode tree = objectMapper.readTree(weatherJson);
        JsonNode node = tree.at("/fact");
        WeatherData weatherData = objectMapper.treeToValue(node, WeatherData.class);
        Weather weather = modelMapper.map(weatherData, Weather.class);

        String nowDt = tree.get("now_dt").asText().substring(0, 19);
        LocalDateTime time = LocalDateTime.parse(nowDt);
        weather.setTime(time);
        return weather;
    }
}
