package ru.ivanov.bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.bot.model.Weather;

/**
 * @author Ivan Ivanov
 **/
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
