package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandSend extends AbstractClientCommand {
    @Override
    public void execute() {
        System.out.print("ENTER THE NAME: ");
        String messageTarget = scanner.nextLine();
        System.out.print("\nENTER THE MESSAGE: ");
        String messageText = scanner.nextLine();
        final boolean isMessageSend;
        isMessageSend = chatService.sendMessage(session, messageTarget, messageText);
        String status = isMessageSend ? "SENT" : "ERROR";
        System.out.println("\nMESSAGE STATUS: " + status);
    }

    public ClientCommandSend(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }
}
