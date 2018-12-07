package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandBroadcast  extends AbstractClientCommand{
    public ClientCommandBroadcast(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        System.out.print("ENTER THE BROADCAST MESSAGE: ");
        String broadcastText = scanner.nextLine();
        chatService.sendBroadcast(session, broadcastText);
        System.out.println("\nBroadcasting attempted.");
    }
}
