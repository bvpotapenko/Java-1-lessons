package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandShowUsers  extends AbstractClientCommand{
    public ClientCommandShowUsers(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        System.out.println("Next users are registered:");
        System.out.println(chatService.getListLogin());
    }
}
