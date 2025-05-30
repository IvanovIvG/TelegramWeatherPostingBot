package ru.ivanov.bot.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * канал в telegram
 * @author Ivan Ivanov
 **/
@Data
@Entity(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * внутренний id канала, например 1280160452
     */
    @Column(name = "channel_id")
    private long channelID;

    /**
     * название канала
     */
    private String channelName;
}
