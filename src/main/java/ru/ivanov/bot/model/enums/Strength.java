package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author Ivan Ivanov
 **/
@Getter
public enum Strength {
    No(0, ""),
    Weak(1, "слабый"),
    Normal(2, ""),
    Strong(3, "сильный"),
    VeryStrong(4, "очень сильный");

    private final float code;
    private final String strengthInRussian;

    Strength(float code, String strengthInRussian) {
        this.code = code;
        this.strengthInRussian = strengthInRussian;
    }

    @JsonValue
    public float getCode() {
        return code;
    }
}
