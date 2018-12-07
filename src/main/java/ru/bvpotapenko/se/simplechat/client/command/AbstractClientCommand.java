package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public abstract class AbstractClientCommand implements Command{
    protected ChatService chatService;
    protected Session session;
    protected Scanner scanner;

    public AbstractClientCommand(ChatService chatService, Session session, Scanner scanner) {
        this.chatService = chatService;
        this.session = session;
        this.scanner = scanner;
    }
}
