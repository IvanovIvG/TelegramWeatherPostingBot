package ru.ivanov.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.bot.model.Weather;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class YandexWeatherService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Weather getCurrentWeather(){
        String url = "https://api.weather.yandex.ru/v2/forecast?lat={lat}&lon={lon}";
        HttpEntity<Void> requestEntity = createRequest();
        Map<String, Float> uriParameters = createParameters();

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class, uriParameters);

        String weatherJson = response.getBody();
        try {
            return parseJson(weatherJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<Void> createRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Yandex-Weather-Key", "9308ba1f-746a-4e0a-8a70-b8bf59e41453");
        return new HttpEntity<>(headers);
    }

    private Map<String, Float> createParameters(){
        Map<String, Float> uriParams = new HashMap<>();
        uriParams.put("lat", 55.755863f);
        uriParams.put("lon", 37.6177f);
        return uriParams;
    }

    private Weather parseJson(String weatherJson) throws JsonProcessingException {
        JsonNode tree = objectMapper.readTree(weatherJson);
        JsonNode node = tree.at("/fact");
        return objectMapper.treeToValue(node, Weather.class);
    }
}
