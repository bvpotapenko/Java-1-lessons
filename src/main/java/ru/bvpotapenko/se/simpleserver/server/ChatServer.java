package ru.bvpotapenko.se.simpleserver.server;

import ru.bvpotapenko.se.simpleserver.server.api.ChatService;
import ru.bvpotapenko.se.simpleserver.service.ChatServiceBean;

import javax.xml.ws.Endpoint;

public class ChatServer {
    public static void main(String[] args) {
        final ChatService service = new ChatServiceBean();
        service.register("admin", "admin");
        service.register("user-1", "user-1");
        service.register("user-2", "user-2");
        Endpoint.publish("http://locahost:8080/ChatService?wsdl", service);
    }
}
