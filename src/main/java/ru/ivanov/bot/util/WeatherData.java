package ru.ivanov.bot.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Ivan Ivanov
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty("temp")
    private int temperature;

    @JsonProperty("wind_speed")
    private int windSpeed;

    @JsonProperty("prec_strength")
    private int precisionStrength;
}
