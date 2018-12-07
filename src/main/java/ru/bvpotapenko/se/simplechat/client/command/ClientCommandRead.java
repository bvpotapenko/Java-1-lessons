package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Message;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ClientCommandRead  extends AbstractClientCommand{
    @Override
    public void execute() {
        List<Message> mb = chatService.getMessages(session);
        for (Message m : mb) {
            System.out.println("*** NEW MESSAGE ***");
            System.out.println("FROM: " + m.source);
            System.out.println("ON: " + LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(m.timestamp), m.getZoneId()));
            System.out.println("TEXT: " + m.text);
        }
    }

    public ClientCommandRead(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }
}
