package ru.bvpotapenko.se.simpleserver.service;

import ru.bvpotapenko.se.simpleserver.server.api.UserService;
import ru.bvpotapenko.se.simpleserver.server.model.User;

import java.util.List;

public class UserServiceBean implements UserService {
    @Override
    public boolean register(String login, String passwoerd) {
        return false;
    }

    @Override
    public boolean isLoginExist(String login) {
        return false;
    }

    @Override
    public boolean removeUser(String login) {
        return false;
    }

    @Override
    public List<String> getListLogin() {
        return null;
    }

    @Override
    public User getUseer(String login) {
        return null;
    }
}
