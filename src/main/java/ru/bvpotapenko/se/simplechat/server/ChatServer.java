package ru.bvpotapenko.se.simplechat.server;

import ru.bvpotapenko.se.simplechat.server.api.ChatService;
import ru.bvpotapenko.se.simplechat.server.service.ChatServiceBean;

import javax.xml.ws.Endpoint;

public class ChatServer {

    public static void main(String[] args) {
        final ChatService service = new ChatServiceBean();
        service.register("admin", "admin");
        service.register("test", "test");
        Endpoint.publish("http://localhost:8080/ChatService?wsdl",service);
    }
}
