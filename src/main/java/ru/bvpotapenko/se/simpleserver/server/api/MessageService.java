package ru.bvpotapenko.se.simpleserver.server.api;

import ru.bvpotapenko.se.simpleserver.server.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessage(String login);

    void sendBroadCast(String source, String text);

    boolean sendMessage(String source, String target, String text);

    boolean cleanMessage(String login);
}
