package ru.bvpotapenko.se.simplechat.client;

import ru.bvpotapenko.se.simplechat.client.command.*;
import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.model.Contact;
import ru.bvpotapenko.se.simplechat.server.model.Message;
import ru.bvpotapenko.se.simplechat.server.model.Session;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class ClientService {

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

    private static final String LOCAL_PART = "ChatServiceBeanService";
    private static final String LOCAL_NAMESPACE = "http://service.server.simplechat.se.bvpotapenko.ru/";
    private static final String WSDL = "http://localhost:8080/ChatService?wsdl";

    private final Map<String, AbstractClientCommand> commands = new HashMap<>();

    private final URL url;
    private final QName qname;
    private final Service service;
    private final ChatService chatService;
    private final Scanner scanner;
    private Session session = null;
    private String username = "";
    private boolean isLoggedIn;

    public ClientService() throws Exception {
        url = new URL(WSDL);
        qname = new QName(LOCAL_NAMESPACE, LOCAL_PART);
        service = Service.create(url, qname);
        chatService = service.getPort(ChatService.class);
        scanner = new Scanner(System.in);
        isLoggedIn = false;

        commands.put(CMD_LOGIN, new ClientCommandLogin(chatService, session, scanner));
        commands.put(CMD_READ, new ClientCommandRead(chatService, session, scanner));
        commands.put(CMD_SEND, new ClientCommandSend(chatService, session, scanner));
        commands.put(CMD_HELP, new ClientCommandHelp(chatService, session, scanner));
        commands.put(CMD_EXIT, new ClientCommandExit(chatService, session, scanner));
        commands.put(CMD_LOGOUT, new CLientCommandLogout(chatService, session, scanner));
        commands.put(CMD_USERS, new ClientCommandShowUsers(chatService, session, scanner));
        commands.put(CMD_BROADCAST, new ClientCommandBroadcast(chatService, session, scanner));
        commands.put(CMD_CONTACTS, new ClientCommandShowContacts(chatService, session, scanner));
        commands.put(CMD_CONTACT_CREATE, new ClientCommandAddContact(chatService, session, scanner));
        commands.put(CMD_CONTACT_REMOVE, new ClientCommandRemoveContact(chatService, session, scanner));
        commands.put(CMD_ALL_CONTACTS_REMOVE, new ClientCommandClearContacts(chatService, session, scanner));
    }

    public void run() {
        String cmd = "";
        help();
        System.out.println();

        while (!CMD_EXIT.equals(cmd)) {
            if (isLoggedIn) {
                int n = getNewMessagesAmount();
                System.out.print(username + (n > 0 ? " [" + n + "]" : "") + " | ENTER CMD:  ");
            } else {
                System.out.print("ENTER CMD: ");
            }
            cmd = scanner.nextLine();
            System.out.println();
            final Command command = commands.get(cmd);
            if (command != null) command.execute();
            else{
                System.out.println("WRONG COMMAND!");
                System.out.println("ENTER 'h' FOR HELP");
            }
            System.out.println();
        }
    }


    private void help() {

    }

    private void login() {
        new ClientCommandLogin(chatService, session, scanner).execute();
        if (session != null) isLoggedIn = true;
        String status = "ERROR";
        if (isLoggedIn) {
            status = "OK";
            username = "YOU ARE: " + chatService.getUser(session).getLogin() + "";
        } else username = "";
        System.out.println("\nAUTH: " + status);

    }

    private void logout() {
       /* isLoggedIn = !chatService.signOut(session) && isLoggedIn;
        final String status = !isLoggedIn ? "OK" : "ERROR";
        System.out.println("LOGOUT: " + status);
        username = "";*/
       new CLientCommandLogout(chatService, session, scanner).execute();
    }

    private void read() {
        if (!isLoggedIn) {
            System.out.println("USER: ANONYMOUS | ACCESS: DENIED!" +
                    "\nYOU NEED TO BE LOGGED IN TO RECEIVE MESSAGES");
            return;
        }
       new ClientCommandRead(chatService, session, scanner).execute();
    }

    private void broadcast() {
        new ClientCommandBroadcast(chatService,session,scanner).execute();
    }

    private void send() {
        new ClientCommandSend(chatService, session, scanner).execute();
    }

    private void showUsers() {
       new ClientCommandShowUsers(chatService, session, scanner).execute();
    }

    private int getNewMessagesAmount() {
        return 0; //// FIXME: 06-Dec-18
    }


    private void showContacts() {
       new ClientCommandShowContacts(chatService, session, scanner).execute();
    }

    private void addContact() {
        new ClientCommandAddContact(chatService, session, scanner).execute();
    }

    private void removeContact() {
        new ClientCommandRemoveContact(chatService, session, scanner).execute();
    }

    private void clearContacts() {
      new  ClientCommandClearContacts(chatService, session, scanner).execute();
    }
}
