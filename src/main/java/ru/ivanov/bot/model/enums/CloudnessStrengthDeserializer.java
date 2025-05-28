package ru.ivanov.bot.model.enums;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * @author Ivan Ivanov
 **/
public class CloudnessStrengthDeserializer extends StdDeserializer<CloudnessStrength> {
    public CloudnessStrengthDeserializer() {
        super(CloudnessStrength.class);
    }

    public CloudnessStrengthDeserializer(Class t) {
        super(t);
    }

    @Override
    public CloudnessStrength deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double code = node.asDouble();
        if(code == 0.75){
            return CloudnessStrength.PartlyClear;
        }

        for (CloudnessStrength strength : CloudnessStrength.values()) {
            if (code == strength.getCode()) {
                return strength;
            }
        }

        return null;    }
}
