package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author Ivan Ivanov
 **/
@Getter
public enum Direction {
    NW("северо-западное", "nw"),
    N("северное", "n"),
    NE("северо-восточное", "ne"),
    E("восточное", "e"),
    SE("юго-восточное", "se"),
    S("южное", "s"),
    SW("юго-западное", "sw"),
    W(" западное", "w"),
    C("штиль", "c");

    private final String directionInRussian;
    private final String code;

    Direction(String directionInRussian, String code) {
        this.directionInRussian = directionInRussian;
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
