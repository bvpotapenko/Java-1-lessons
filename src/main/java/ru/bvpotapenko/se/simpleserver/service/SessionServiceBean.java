package ru.bvpotapenko.se.simpleserver.service;

import ru.bvpotapenko.se.simpleserver.server.api.SessionService;
import ru.bvpotapenko.se.simpleserver.server.api.UserService;
import ru.bvpotapenko.se.simpleserver.server.model.Session;
import ru.bvpotapenko.se.simpleserver.server.model.User;

public class SessionServiceBean implements SessionService {

    public SessionServiceBean(UserService userService){

    }

    @Override
    public Session signIn(String login, String password) {
        return null;
    }

    @Override
    public User getUser(Session session) {
        return null;
    }

    @Override
    public boolean signOut(Session session) {
        return false;
    }
}
