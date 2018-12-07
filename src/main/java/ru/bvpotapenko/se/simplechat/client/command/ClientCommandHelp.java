package ru.bvpotapenko.se.simplechat.client.command;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import java.util.Scanner;

public class ClientCommandHelp extends AbstractClientCommand {
    private static final String CMD_EXIT = "exit";
    private static final String CMD_HELP = "h";
    private static final String CMD_LOGIN = "login";
    private static final String CMD_LOGOUT = "logout";
    private static final String CMD_READ = "read";
    private static final String CMD_SEND = "send";
    private static final String CMD_USERS = "users";
    private static final String CMD_BROADCAST = "broadcast";
    private static final String CMD_CONTACTS = "contacts";
    private static final String CMD_CONTACT_CREATE = "contact-create";
    private static final String CMD_CONTACT_REMOVE = "contact-remove";
    private static final String CMD_ALL_CONTACTS_REMOVE = "all-contacts-remove";

    public ClientCommandHelp(ChatService chatService, Session session, Scanner scanner) {
        super(chatService, session, scanner);
    }

    @Override
    public void execute() {
        System.out.println(
                "*-=WELCOME TO CHAT=-*\n" +
                        "TO START CHATTING YOU NEED TO BE LOGGED IN.\n" +
                        "YOU CAN USE NEXT COMMANDS:\n\n" +
                        CMD_HELP + " - to see this message\n" +
                        CMD_EXIT + " - to exit\n" +
                        CMD_LOGIN + " - to login\n" +
                        CMD_LOGOUT + " - to logout\n" +
                        CMD_USERS + " - to get user list\n" +
                        CMD_SEND + " - to send a private message\n" +
                        CMD_BROADCAST + " - to send a broadcast message\n" +
                        CMD_READ + " - to receive new messages" +
                        CMD_BROADCAST + " - to broadcast\n" +
                        CMD_CONTACTS + " - to see all your contacts \n" +
                        CMD_CONTACT_CREATE + " - to add a contact \n" +
                        CMD_CONTACT_REMOVE + " - to remove a contact \n" +
                        CMD_ALL_CONTACTS_REMOVE + " - to clear your contact list from all the contacts"
        );
    }
}
