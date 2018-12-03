package ru.bvpotapenko.se.simpleserver.server.model;

import java.time.LocalDate;
import java.util.UUID;

public class Message {

    public String id = UUID.randomUUID().toString();

    public Long timestamp = System.currentTimeMillis();

    public String source;

    public String target;

    public String text;

    public Message(String source, String target, String text) {
        this.source = source;
        this.target = target;
        this.text = text;
    }

    public Message(){

    }

    @Override
    public String toString(){
        LocalDate date = LocalDate.ofEpochDay(timestamp);
        return source + "("+date.toString()+") says:\n" + text;
    }


}
