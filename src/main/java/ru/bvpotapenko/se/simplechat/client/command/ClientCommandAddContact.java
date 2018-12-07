package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandAddContact extends AbstractClientCommand{
    public ClientCommandAddContact(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        if (session == null) return;
        System.out.print("ENTER CONTACT'S LOGIN: ");
        final String contactLoginToAdd = scanner.nextLine();
        chatService.addContact(session, contactLoginToAdd);
        System.out.println();
    }
}
