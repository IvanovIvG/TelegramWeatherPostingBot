package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Ivan Ivanov
 **/
@Getter
public enum PhenomenaCondition {
    fog("туман", "fog"),
    mist("дымка", "mist"),
    smoke("смог", "smoke"),
    dust("пыль", "dust"),
    dustSuspension("пылевая взвесь", "dust-suspension"),
    duststorm("пыльная буря", "duststorm"),
    thunderstormWithDuststorm("пыльная буря с грозой", "thunderstorm-with-duststorm"),
    driftingSnow("слабая метель", "drifting-snow"),
    blowingSnow("метель", "blowing-snow"),
    icePellets("ледяная крупа", "ice-pellets"),
    freezingRain("ледяной дождь", "freezing-rain"),
    tornado("торнадо", "tornado"),
    volcanicAsh("вулканический пепел", "volcanic-ash");

    private final String phenomenaInRussia;
    private final String code;

    PhenomenaCondition(String phenomenaInRussia, String code) {
        this.phenomenaInRussia = phenomenaInRussia;
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
