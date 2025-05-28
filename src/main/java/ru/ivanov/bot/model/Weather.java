package ru.ivanov.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import ru.ivanov.bot.model.enums.*;
import ru.ivanov.bot.util.CustomDateDeserializer;

import java.time.LocalDateTime;

/**
 * @author Ivan Ivanov
 **/
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * время измерения погоды
     */
    @JsonProperty("obs_time")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDateTime measurementTime;

    /**
     * температура в &deg;C
     */
    @JsonProperty("temp")
    private float temperature;

    /**
     * как ощущается
     */
    @JsonProperty("feels_like")
    private float temperatureFeels;

    /**
     * название иконки описания погоды
     */
    @JsonProperty("icon")
    private String weatherIcon;

    /**
     * описание погоды
     */
    @JsonProperty("condition")
    private WeatherCondition weatherCondition;

    /**
     * название иконки дополнительной погоды
     */
    @JsonProperty("phenom_icon")
    private String phenomIcon;

    /**
     * описание дополнительной погоды
     */
    @JsonProperty("phenom_condition")
    private PhenomenaCondition phenomCondition;

    /**
     * скорость ветра в м/c
     */
    @JsonProperty("wind_speed")
    private float windSpeed;

    /**
     * порывы ветра в м/с
     */
    @JsonProperty("wind_gust")
    private float windGust;

    /**
     * направление
     */
    @JsonProperty("wind_dir")
    private Direction windDirection;

    /**
     * давление в мм рт.с
     */
    @JsonProperty("pressure_mm")
    @Column(name = "pressure_mm")
    private float pressureMM;

    /**
     * давление в гектопаскалях
     */
    @JsonProperty("pressure_pa")
    private float pressurePa;

    /**
     * влажность воздуха в прцентах
     */
    @JsonProperty("humidity")
    private float humidity;

    /**
     * есть ли гроза
     */
    @JsonProperty("is_thunder")
    private boolean isThunder;

    /**
     * тип осадков
     */
    @JsonProperty("prec_type")
    private PrecisionType precisionType;

    /**
     * сила осадков
     */
    @JsonProperty("prec_strength")
    private Strength precisionStrength;

    /**
     * облачность
     */
    @JsonProperty("cloudness")
    private CloudnessStrength cloudness;
}
