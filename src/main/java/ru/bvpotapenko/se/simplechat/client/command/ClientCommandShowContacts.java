package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Contact;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;
import java.util.Set;

public class ClientCommandShowContacts  extends AbstractClientCommand{
    public ClientCommandShowContacts(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        if (session == null) return;
        final Set<Contact> contacts = chatService.getContacts(session);
        System.out.println("YOUR CONTACTS:");
        for (final Contact c : contacts) {
            if (c == null) continue;
            System.out.println(c.target);
        }
        System.out.println();
    }
}
