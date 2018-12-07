package ru.bvpotapenko.se.simplechat.server.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.time.LocalDate;

public final class Message {
    public String id = UUID.randomUUID().toString();

    public Long timestamp = System.currentTimeMillis();

    public String source;

    public String target;

    public String text;

    private ZoneId zoneId;

    public Message(String source, String target, String text) {
        this.source = source;
        this.target = target;
        this.text = text;
        zoneId = ZoneId.systemDefault();
    }

    public Message(){
        zoneId = ZoneId.systemDefault();
    }

    @Override
    public String toString(){
        LocalDateTime date = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp), zoneId);
        return source + "("+ date+") says:\n" + text;
    }

    public ZoneId getZoneId (){
        return zoneId;
    }

}
