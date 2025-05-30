package ru.ivanov.bot.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.ivanov.bot.model.SubscribedUser;

/**
 * @author Ivan Ivanov
 **/
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper configureModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, SubscribedUser> propertyMapper = modelMapper.createTypeMap(User.class, SubscribedUser.class);
        propertyMapper.addMappings(mapper -> mapper.skip(SubscribedUser::setId));
        return modelMapper;
    }
}
