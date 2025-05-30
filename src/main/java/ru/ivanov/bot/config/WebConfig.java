package ru.ivanov.bot.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ivan Ivanov
 **/
@Configuration
public class WebConfig {
    @Bean
    public RestTemplate configRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper configObjectMapper(){
        return new ObjectMapper().findAndRegisterModules().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
