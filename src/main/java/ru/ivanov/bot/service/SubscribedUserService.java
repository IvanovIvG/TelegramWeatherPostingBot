package ru.ivanov.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.bot.model.SubscribedUser;
import ru.ivanov.bot.repositories.SubscribedUserRepository;

/**
 * @author Ivan Ivanov
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class SubscribedUserService {
    private final SubscribedUserRepository repository;

    public void subscribeUser(SubscribedUser userToSubscribe) {
        repository.save(userToSubscribe);
    }

    public void unsubscribeUser(long userID) {
        repository.deleteByUserID(userID);
    }

    public boolean userIsNotSubscribed(long userID) {
        return repository.findByUserID(userID).isEmpty();
    }
}
