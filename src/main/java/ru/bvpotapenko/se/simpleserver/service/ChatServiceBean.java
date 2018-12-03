package ru.bvpotapenko.se.simpleserver.service;

import ru.bvpotapenko.se.simpleserver.server.api.ChatService;
import ru.bvpotapenko.se.simpleserver.server.api.MessageService;
import ru.bvpotapenko.se.simpleserver.server.api.SessionService;
import ru.bvpotapenko.se.simpleserver.server.api.UserService;
import ru.bvpotapenko.se.simpleserver.server.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.bvpotapenko.se.simpleserver.server.api.ChatService")
public class ChatServiceBean implements ChatService {

    private final UserService userService = new UserServiceBean();

    private final MessageService messageService = new MessageServiceBean(userService);

    private final SessionService sessionService = new SessionServiceBean(userService);

    @Override
    @WebMethod
    public boolean register(final String login, final String password) {
        return userService.register(login, password);
    }

    @Override
    @WebMethod
    public List<String> getListLogin() {
        return userService.getListLogin();
    }

    @Override
    @WebMethod
    public boolean isLoginExists(final String login) {
        return userService.isLoginExist(login);
    }

    @Override
    @WebMethod
    public Session signIn(final String login, final String password) {
        return sessionService.signIn(login, password);
    }

    @Override
    @WebMethod
    public User getUser(final Session session) {
        return sessionService.getUser(session);
    }

    @Override
    @WebMethod
    public boolean signOut(final Session session) {
        return sessionService.signOut(session);
    }

    @Override
    @WebMethod
    public List<Message> getMessages(final Session session) {
        final User user = sessionService.getUser(session);
        return messageService.getMessage(user.getLogin());
    }

    @Override
    @WebMethod
    public void sendBroadCast(final Session session, final String text) {
        User user = sessionService.getUser(session);
        messageService.sendBroadCast(user.getLogin(), text);
    }

    @Override
    @WebMethod
    public boolean sendMessage(final Session session, final String target, final String text) {
        if(session == null || target == null || target == "") return false;
        User sourceUser = sessionService.getUser(session);
        User targetUser = userService.getUser(target); //Do it to "check" our user.
        if (sourceUser == null || targetUser == null) return false;
        return messageService.sendMessage(sourceUser.getLogin(), targetUser.getLogin(), text);
    }

    @Override
    @WebMethod
    public boolean cleanMessage(final Session session) {
        User user = sessionService.getUser(session);
        return messageService.cleanMessage(user.getLogin());
    }

    public ChatServiceBean(){

    }

}
