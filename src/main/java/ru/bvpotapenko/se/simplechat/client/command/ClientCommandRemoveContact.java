package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandRemoveContact  extends AbstractClientCommand{
    public ClientCommandRemoveContact(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        if (session == null) return;
        System.out.print("ENTER CONTACT'S LOGIN: ");
        final String contactLoginToRemove = scanner.nextLine();
        chatService.removeContact(session, contactLoginToRemove);
        System.out.println();
    }
}
