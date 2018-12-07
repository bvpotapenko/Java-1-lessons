package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandLogin extends AbstractClientCommand{

    public ClientCommandLogin(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        System.out.print("ENTER LOGIN: ");
        String login = scanner.nextLine();
        System.out.print("\nENTER PASSWORD: ");
        String password = scanner.nextLine();
        session = chatService.signIn(login, password);
    }
}
