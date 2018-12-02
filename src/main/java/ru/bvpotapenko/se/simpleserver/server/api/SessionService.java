package ru.bvpotapenko.se.simpleserver.server.api;

import ru.bvpotapenko.se.simpleserver.server.model.Session;
import ru.bvpotapenko.se.simpleserver.server.model.User;

public interface SessionService {

    Session signIn (String login, String password);

    User getUser(Session session);

    boolean signOut(Session session);
}
