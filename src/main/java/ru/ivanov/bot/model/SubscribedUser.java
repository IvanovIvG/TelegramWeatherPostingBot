package ru.ivanov.bot.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * пользователь телеграмма - клиент бота
 * @author Ivan Ivanov
 **/
@Data
@Entity(name = "users")
public class SubscribedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * внутренний id пользователя, например 1280160452
     */
    @Column(name = "user_id")
    private long userID;

    /**
     * username пользователя, например ivanvanich
     */
    private String username;

    /**
     * имя пользователя
     */
    private String firstName;

    /**
     * фамилия пользователя
     */
    private String lastName;
}
