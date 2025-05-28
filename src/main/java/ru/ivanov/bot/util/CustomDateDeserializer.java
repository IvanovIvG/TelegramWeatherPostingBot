package ru.ivanov.bot.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Ivan Ivanov
 **/
public class CustomDateDeserializer extends StdDeserializer<LocalDateTime> {
    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        long date = Long.parseLong(jsonParser.getText());

        return LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.of("+3"));
    }
}
