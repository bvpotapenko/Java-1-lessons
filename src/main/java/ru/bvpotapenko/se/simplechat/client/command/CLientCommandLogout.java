package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class CLientCommandLogout  extends AbstractClientCommand{
    public CLientCommandLogout(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        chatService.signOut(session);
    }
}
