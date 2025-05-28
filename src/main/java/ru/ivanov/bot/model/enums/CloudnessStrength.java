package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

/**
 * @author Ivan Ivanov
 **/
@Getter
@JsonDeserialize(using = CloudnessStrengthDeserializer.class)
public enum CloudnessStrength {
    Clear("ясно", 0),
    PartlyCloudy("малооблачно", 0.25F),
    PartlyClear("переменная облачность", 0.5f),
    Cloudy("облачно", 1);

    private final String cloudnessStrengthInRussia;
    private final float code;

    CloudnessStrength(String strengthInRussia, float code) {
        cloudnessStrengthInRussia = strengthInRussia;
        this.code = code;
    }

    @JsonValue
    public float getCode() {
        return code;
    }
}
