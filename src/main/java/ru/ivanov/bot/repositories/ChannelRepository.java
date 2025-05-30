package ru.ivanov.bot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.bot.model.Channel;

/**
 * @author Ivan Ivanov
 **/
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
