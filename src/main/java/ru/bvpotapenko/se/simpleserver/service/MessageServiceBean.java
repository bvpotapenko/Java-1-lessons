package ru.bvpotapenko.se.simpleserver.service;

import ru.bvpotapenko.se.simpleserver.server.api.MessageService;
import ru.bvpotapenko.se.simpleserver.server.api.UserService;
import ru.bvpotapenko.se.simpleserver.server.model.Message;

import java.util.List;

public class MessageServiceBean implements MessageService {

    public MessageServiceBean(UserService userService){

    }

    @Override
    public List<Message> getMessage(String login) {
        return null;
    }

    @Override
    public boolean sendBroadCast(String source, String text) {
        return false;
    }

    @Override
    public boolean sendMessage(String source, String target, String text) {
        return false;
    }

    @Override
    public boolean cleanMessage(String login) {
        return false;
    }
}
