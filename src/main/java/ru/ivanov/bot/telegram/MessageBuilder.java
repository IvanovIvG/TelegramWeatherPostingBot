package ru.ivanov.bot.telegram;

import org.springframework.stereotype.Component;
import ru.ivanov.bot.model.Weather;
import ru.ivanov.bot.model.enums.PrecisionType;
import ru.ivanov.bot.model.enums.Strength;

/**
 * @author Ivan Ivanov
 **/
@Component
public class MessageBuilder {
    public String getWeatherMessage(Weather w) {
        String phenomCondition = getPhenomCondition(w);
        String thunder = getThunder(w);
        String precision = getPrecision(w);
        String cloudness = getCloudness(w);

        return """
                В Москве по данным на %02d:%02d погода следующая:
                В городе %s%s
                Температура воздуха равна %.1f %cC, ощущается как %.1f %cC
                Скорость ветра равна %.1f м/c c порывами до %.1f м/с
                Направление %s
                Давление %.1f мм рт.с. Влажность воздуха %.1f%%.
                %s%s%s
                """.formatted(w.getMeasurementTime().getHour(), w.getMeasurementTime().getMinute(),
                w.getWeatherCondition().getWeatherInRussian(), phenomCondition,
                w.getTemperature(), '°',
                w.getTemperatureFeels(), '°',
                w.getWindSpeed(), w.getWindGust(), w.getWindDirection().getDirectionInRussian(),
                w.getPressureMM(), w.getHumidity(),
                thunder, precision,
                cloudness);
    }

    private String getPhenomCondition(Weather w) {
        String phenomCondition = "";
        if (w.getPhenomCondition() != null) {
            phenomCondition = ", " + w.getPhenomCondition().getPhenomenaInRussia();
        }
        return phenomCondition;
    }

    private String getThunder(Weather w) {
        String thunder = "";
        if (w.isThunder()) {
            thunder = "Гроза, ";
        }
        return thunder;
    }

    private String getPrecision(Weather w){
        if(w.getPrecisionStrength()== Strength.No || w.getPrecisionType()== PrecisionType.No){
            return "";
        }
        String precision =  w.getPrecisionStrength().getStrengthInRussian() + " " +
                w.getPrecisionType().getPrecisionInRussia() + "\n";
        return firstLetterToUppercase(precision);
    }

    private String firstLetterToUppercase(String str) {
        String firstChar = str.substring(0, 1).toUpperCase();
        String rest = str.substring(1);
        return firstChar + rest;
    }

    private String getCloudness(Weather w) {
        return firstLetterToUppercase(w.getCloudness().getCloudnessStrengthInRussia());
    }
}
