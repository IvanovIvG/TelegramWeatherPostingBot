package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Ivan Ivanov
 **/
@Getter
public enum PrecisionType {
    No("без осадков", 0),
    Rain("дождь", 0.25f),
    RainWithSnow("дождь со снегом", 0.5f),
    Snow("снег", 0.75f),
    Hail("град", 1);

    private final String precisionInRussia;
    private final float code;

    PrecisionType(String precisionInRussia, float code) {
        this.precisionInRussia = precisionInRussia;
        this.code = code;
    }

    @JsonValue
    public float getCode() {
        return code;
    }
}
