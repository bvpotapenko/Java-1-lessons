package ru.bvpotapenko.se.simpleserver.server.api;

import ru.bvpotapenko.se.simpleserver.server.model.*;


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
    void sendBroadCast(Session session, String text);

    @WebMethod
    boolean sendMessage(Session session, String target, String text);

    @WebMethod
    boolean cleanMessage(Session session);
}
