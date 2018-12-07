package ru.bvpotapenko.se.simplechat.server.api;

import ru.bvpotapenko.se.simplechat.server.model.Session;
import ru.bvpotapenko.se.simplechat.server.model.User;

public interface SessionService {

    Session signIn (String login, String password);

    User getUser(Session session);

    boolean signOut(Session session);

}
