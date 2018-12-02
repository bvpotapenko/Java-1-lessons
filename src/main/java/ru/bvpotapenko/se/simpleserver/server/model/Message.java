package ru.bvpotapenko.se.simpleserver.server.model;

import java.util.UUID;

public class Message {

    public String id = UUID.randomUUID().toString();

    public Long timestamp = System.currentTimeMillis();

    public String source;

    public String target;

    public String text;
}
