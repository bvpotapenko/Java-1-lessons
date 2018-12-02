package ru.bvpotapenko.se.simpleserver.service;

import ru.bvpotapenko.se.simpleserver.server.api.UserService;
import ru.bvpotapenko.se.simpleserver.server.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceBean implements UserService {

    /*
    * TODOs:
    * TODO: 0. Why logins are used as keys for the HashMap?
    * TODO: 1. Replace return values with error codes for functions, that provide boolean as a flag of success.
    * TODO: 2. Make this codes FINAL constants.
    * TODO: 3. Make a class, providing descriptions for this errors.
    * TODO: 4. Think of "Letter of confirmation" for an E-mail
    * TODO: 5. Add a field for an e-mail address.
    * TODO: 6. Check new passwords' complexity.
    * */
    private final Map<String, User> users = new HashMap<>();

    @Override
    public boolean register(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if (users.containsKey(login)) return false;
        return createUser(login, password);
    }

    // TODO: 03-Dec-18 Why is this method public?
    public final boolean createUser(final String login, final String password){
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if (users.containsKey(login)) return false;
        final User user = new User(login, password);
        users.put(login, user);
        return true;
    }

    @Override
    public boolean isLoginExist(String login) {
        if (login == null || login.isEmpty()) return false;
        return users.containsKey(login);
    }

    // TODO: 03-Dec-18 Check if we need any sessions workarounds for it
    @Override
    public boolean removeUser(String login) {
        if (login == null || login.isEmpty()) return false;
        if (users.containsKey(login)){
            final User user = users.get(login);
            return users.remove(login, user);
        }
        return false;
    }

    @Override
    public List<String> getListLogin() {
        return new ArrayList<>(users.keySet());
    }

    // TODO: 03-Dec-18 compare with an example
    @Override
    public User getUser(String login) {
        if (login == null || login.isEmpty()) return null;
        if (users.containsKey(login))
            return users.get(login);
        return null;
    }

    @Override
    public int getCountUser() {
        return users.size();
    }

}
