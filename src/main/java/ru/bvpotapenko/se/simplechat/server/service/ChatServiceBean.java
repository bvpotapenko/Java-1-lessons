package ru.bvpotapenko.se.simplechat.server.service;

import ru.bvpotapenko.se.simplechat.server.api.*;
import ru.bvpotapenko.se.simplechat.server.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "ru.bvpotapenko.se.simplechat.server.api.ChatService")
public final class ChatServiceBean implements ChatService {

    private final UserService userService = new UserServiceBean();

    private final MessageService messageService = new MessageServiceBean(userService);

    private final SessionService sessionService = new SessionServiceBean(userService);

    private final ContactService contactService = new ContactServiceBean();

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
        System.out.println(sessionService.getUser(session));//// FIXME: 05-Dec-18 cleanup
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
    public int getNewMessagesAmount(final Session session){
        final User user = sessionService.getUser(session);
       return messageService.getNewMessagesAmount(user.getLogin());
    }

    @Override
    @WebMethod
    public void sendBroadcast(final Session session, final String text) {
        User user = sessionService.getUser(session);
        messageService.sendBroadcast(user.getLogin(), text);
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

    @Override
    public ContactBox getContacts(Session session) {
        if (session == null) return null;
        ContactBox contactBox = contactService.getContacts(sessionService.getUser(session).getLogin());
        return contactBox;
    }

    @Override
    public Contact addContact(Session session, String target) {
        if (session == null || target == null || target.isEmpty()) return null;
        Contact contact = contactService.addContact(sessionService.getUser(session).getLogin(),target);
        return contact;
    }

    @Override
    public boolean removeContact(Session session, String target) {
        if (session == null || target == null || target.isEmpty()) return false;
        return contactService.removeContact(sessionService.getUser(session).getLogin(), target);
    }

    @Override
    public boolean removeAllContacts(Session session) {
        if (session == null) return false;
        return contactService.removeAllContacts(sessionService.getUser(session).getLogin());
    }

    public ChatServiceBean(){

    }
}
