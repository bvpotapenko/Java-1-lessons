package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandGetNewMessagesAmount  extends AbstractClientCommand{
    public ClientCommandGetNewMessagesAmount(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        int i=0;
        if (session == null) return;
        i = chatService.getNewMessagesAmount(session);
    }
}
