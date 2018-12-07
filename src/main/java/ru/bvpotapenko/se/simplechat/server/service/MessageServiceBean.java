package ru.bvpotapenko.se.simplechat.server.service;

import ru.bvpotapenko.se.simplechat.server.api.MessageService;
import ru.bvpotapenko.se.simplechat.server.api.UserService;
import ru.bvpotapenko.se.simplechat.server.model.Message;
import ru.bvpotapenko.se.simplechat.server.model.MessageBox;
import ru.bvpotapenko.se.simplechat.server.model.User;

import java.util.*;

//TODO Check over exceeding "null-checks"
public final class MessageServiceBean implements MessageService {

    private final Map<String, MessageBox> boxes = new HashMap<>();
    private final UserService userService;

    public MessageServiceBean(final UserService userService){
        this.userService = userService;
    }

    public MessageBox getMessageBox(final String login) {
        if (login == null || login.isEmpty()) return null;
        if (!boxes.containsKey(login)) boxes.put(login, new MessageBox());
        return boxes.get(login);
    }

    public List<Message> getMessage (final String login){
        if (login == null || login.isEmpty()) return null;
        final User user = userService.getUser(login);
        if (user == null) return null;
        final MessageBox messageBox = getMessageBox(user.getLogin());
        cleanMessage(user.getLogin());
        return messageBox;
    }

    //TODO
    @Override
    public void sendBroadcast(final String source, final String text) {
        if (source == null || source.isEmpty()) return;
        if (text == null) return;
        for (String target: userService.getListLogin()) {
            if (source.equals(target)) continue;
            sendMessage(source, target, text);
        }
    }

    //TODO: Check the message for any illegal characters.
    @Override
    public boolean sendMessage(final String source, final String target, final String text) {
        if (source == null || source.isEmpty()) return false;
        if (target == null || target.isEmpty()) return false;
        if (text == null) return false;
        final User sourceUser = userService.getUser(source);
        final User targetUser = userService.getUser(target);
        if (sourceUser == null || targetUser == null) return false;
        final Message message = new Message(sourceUser.getLogin(), targetUser.getLogin(), text);
        final MessageBox messageBox = getMessageBox(targetUser.getLogin());
        messageBox.add(message);
        return true;
    }

    @Override
    public boolean cleanMessage(final String login) {
        if (login == null || login.isEmpty()) return false;
        final User user = userService.getUser(login);
        if (user == null) return false;
        boxes.put(user.getLogin(), new MessageBox());
        return true;
    }

    @Override
    public int getNewMessagesAmount(final String login){
        return this.getMessageBox(login).size();
    }
}
