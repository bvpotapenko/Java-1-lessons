package ru.bvpotapenko.se.simpleserver.client;

import ru.bvpotapenko.se.simpleserver.server.api.ChatService;
import ru.bvpotapenko.se.simpleserver.server.model.Message;
import ru.bvpotapenko.se.simpleserver.server.model.MessageBox;
import ru.bvpotapenko.se.simpleserver.server.model.Session;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ClientService {
    private static final String CMD_EXIT = "exit";
    private static final String CMD_HELP = "h";
    private static final String CMD_LOGIN = "login";
    private static final String CMD_LOGOUT = "logout";
    private static final String CMD_READ = "read";
    private static final String CMD_SEND = "send";
    private static final String CMD_USERS = "users";
    private static final String CMD_BROADCAST = "droadcast";
    private static final String LOCAL_PART = "ChatServiceBeanSearvice";
    private static final String LOCAL_NAMESPACE = "http://service.simpleserver.se.bvpotapenko.ru/";
    private static final String WSDL = "http://localhost:8080/ChatService?wsdl";

    private final URL url;
    private final QName qname;
    private final Service service;
    private final ChatService chatService;
    private final Scanner scanner;
    private Session session = null;

    public ClientService () throws Exception {
        url = new URL(WSDL);
        qname = new QName(LOCAL_NAMESPACE, LOCAL_PART);
        service = Service.create(url, qname);
        chatService = service.getPort(ChatService.class);
        scanner = new Scanner(System.in);
    }

    public void run(){
        String cmd = "";
        help();
        while (!CMD_EXIT.equals(cmd)){
            System.out.println("ENTER CMD (h for help):");
            cmd = scanner.nextLine();
            switch(cmd){
                case CMD_HELP:
                    help();
                case CMD_LOGIN:
                    login();
                    break;
                case CMD_LOGOUT:
                    logout();
                    break;
                case CMD_READ:
                    read();
                    break;
                case CMD_BROADCAST:
                    broadcast();
                    break;
                case CMD_SEND:
                    send();
                    break;
                case CMD_USERS:
                    showUsers();
                    break;
                default:
                    System.out.println("WRONG CMD");
                    help();
            }
            System.out.println();
        }
    }

    private void help(){
        System.out.println(
            CMD_HELP + " - to see this message\n" +
            CMD_EXIT + " - to exit\n" +
            CMD_LOGIN + " - to login\n" +
            CMD_LOGOUT + " - to logout\n" +
            CMD_USERS + " - to get user list\n" +
            CMD_SEND + " - to send a private message\n" +
            CMD_BROADCAST + " - to send a broadcast message\n" +
            CMD_READ + " - to receive new messages"
        );
    }
    private void login(){
        System.out.println("ENTER LOGIN:");
        String login = scanner.nextLine();
        System.out.println("ENTER PASSWORD:");
        String password = scanner.nextLine();
        session = chatService.signIn(login, password);
        final String status = session != null ? "OK" : "ERROR";
        System.out.println("AUTH: " + status);
    }

    private void logout(){
        boolean isLoggedOut = chatService.signOut(session);
        final String status = isLoggedOut ? "OK" : "ERROR";
        System.out.println("LOGOUT: " + status);
    }

    private void read(){
        List<Message> mb = chatService.getMessages(session);
        for (Message m: mb) {
            System.out.println("*** NEW MESSAGE ***");
            System.out.println("FROM: " + m.source);
            System.out.println("ON: " + LocalDate.ofEpochDay(m.timestamp));
            System.out.println("TEXT: " + m.text);
        }
    }

    private void broadcast(){
        System.out.println("ENTER THE BROADCAST MESSAGE:");
        String broadcastText = scanner.nextLine();
        chatService.sendBroadCast(session, broadcastText);
        System.out.println("Broadcasting attempted.");
    }

    private void send(){
        System.out.println("ENTER THE NAME:");
        String messageTarget = scanner.nextLine();
        System.out.println("ENTER THE MESSAGE:");
        String messageText = scanner.nextLine();
        final boolean isMessageSend;
        isMessageSend = chatService.sendMessage(session, messageTarget, messageText);
        String status = isMessageSend ? "SENT" : "ERROR";
        System.out.println("MESSAGE STATUS: " + status);
    }

    private void showUsers(){
        System.out.println("Next users are registered:");
        System.out.println(chatService.getListLogin());
    }

}
