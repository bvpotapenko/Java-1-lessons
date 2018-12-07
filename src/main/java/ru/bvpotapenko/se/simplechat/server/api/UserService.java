package ru.bvpotapenko.se.simplechat.server.api;

import ru.bvpotapenko.se.simplechat.server.model.User;

import java.util.List;

public interface UserService {

    boolean register (String login, String password);

    boolean isLoginExist(String login);

    boolean removeUser (String login);

    List<String> getListLogin();

    User getUser(String login);

    int getCountUser();
}
