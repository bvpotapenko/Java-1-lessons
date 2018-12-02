package ru.bvpotapenko.se.simpleserver.server.model;

//TODO: 1. Add roles (1. set of permissions, 2. class of roles, 3. set of roles)
//FIXME: 03-Dec-18 2. Use hash instead of passwords
public final class User{

    private String login;

    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}