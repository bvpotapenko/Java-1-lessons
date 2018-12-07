package ru.bvpotapenko.se.simplechat.server.service;

import ru.bvpotapenko.se.simplechat.server.api.SessionService;
import ru.bvpotapenko.se.simplechat.server.api.UserService;
import ru.bvpotapenko.se.simplechat.server.model.Session;
import ru.bvpotapenko.se.simplechat.server.model.User;

import java.util.*;

public final class SessionServiceBean implements SessionService {


    private final UserService userSerevice;
    private final Map<Session, User> sessions = new HashMap<>();


    public SessionServiceBean(final UserService userService){
        this.userSerevice = userService;
    }

    //TODO: 1. What if the user is already logged in?
    // Can we have several sessions for one user?
    // 2. What is the lifespan of a session?
    // 3. Logout on all devices.
    @Override
    public Session signIn(final String login, final String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        final User user = userSerevice.getUser(login);
        if (user == null) return null;
        if(!password.equals(user.getPassword())) return null;
        final Session session = new Session();
        sessions.put(session, user);
        return session;
    }

    @Override
    public User getUser(final Session session) {
        if(session == null) return null;
        return sessions.get(session);
    }

    @Override
    public boolean signOut(final Session session) {
        if(session == null) return false;
        sessions.remove(session);
        return true;
    }
}
