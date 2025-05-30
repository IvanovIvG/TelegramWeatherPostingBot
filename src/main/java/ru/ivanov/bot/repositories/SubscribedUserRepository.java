package ru.ivanov.bot.repositories;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.bot.model.SubscribedUser;

import java.util.Optional;

/**
 * @author Ivan Ivanov
 **/
@Registered
public interface SubscribedUserRepository extends JpaRepository<SubscribedUser, Integer> {
    void deleteByUserID(long userID);
    Optional<SubscribedUser> findByUserID(long userID);
}
