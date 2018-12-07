package ru.bvpotapenko.se.simplechat.server.api;

import ru.bvpotapenko.se.simplechat.server.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessage(String login);

    void sendBroadcast(String source, String text);

    boolean sendMessage(String source, String target, String text);

    boolean cleanMessage(String login);

    int getNewMessagesAmount(String login);
}
