package ru.bvpotapenko.se.simplechat.server.api;

import ru.bvpotapenko.se.simplechat.server.model.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ChatService {

    @WebMethod
    boolean register(@WebParam String login, @WebParam String password);

    @WebMethod
    List<String> getListLogin ();

    @WebMethod
    boolean isLoginExists(@WebParam String login);

    @WebMethod
    Session signIn (@WebParam String login, @WebParam String password);

    @WebMethod
    User getUser (@WebParam Session session);

    @WebMethod
    boolean signOut(@WebParam Session session);

    @WebMethod
    List<Message> getMessages (@WebParam Session session);

    @WebMethod
    int getNewMessagesAmount(@WebParam Session session);

    @WebMethod
    void sendBroadcast(@WebParam Session session, @WebParam String text);

    @WebMethod
    boolean sendMessage(@WebParam Session session, @WebParam String target, @WebParam String text);

    @WebMethod
    boolean cleanMessage(@WebParam Session session);

    @WebMethod
    ContactBox getContacts (@WebParam Session session);

    @WebMethod
    Contact addContact(@WebParam Session session, @WebParam String target);

    @WebMethod
    boolean removeContact(@WebParam Session session, @WebParam String target);

    @WebMethod
    boolean removeAllContacts(@WebParam Session session);
}
