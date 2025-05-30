package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author Ivan Ivanov
 **/
@Getter
public enum WeatherCondition {
    Clear("ясно", "clear"),
    PartlyCloudy("малооблачно", "partly-cloudy"),
    Cloudy("облачно с прояснениями", "cloudy"),
    Overcast("пасмурно", "overcast"),
    LightRain("небольшой дождь", "light-rain"),
    Rain("дождь", "rain"),
    HeavyRain("сильный дождь", "heavy-rain"),
    Showers("ливень", "showers"),
    WetSnow("дождь со снегом", "wet-snow"),
    LightSnow("небольшой снег", "light-snow"),
    Snow("снег", "snow"),
    SnowShowers("снегопад", "snow-showers"),
    Hail("град", "hail"),
    ThunderStorm("гроза", "thunderstorm"),
    ThunderstormWithRain("дождь с грозой", "thunderstorm-with-rain"),
    ThunderstormWithHail("гроза с градом", "thunderstorm-with-hail");

    private final String weatherInRussian;
    private final String code;

    WeatherCondition(String weatherInRussian, String code) {
        this.weatherInRussian = weatherInRussian;
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
