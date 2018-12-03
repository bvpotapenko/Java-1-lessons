package ru.bvpotapenko.se.simpleserver;

import org.junit.jupiter.api.Test;

import ru.bvpotapenko.se.simpleserver.server.api.ChatService;
import ru.bvpotapenko.se.simpleserver.server.model.Session;
import ru.bvpotapenko.se.simpleserver.service.ChatServiceBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ChatServiceTest {
    //TODO  1. add more tests!!
    //      2. use @BeforeAll

    @Test
    public void testChatService() {
        final ChatService service = new ChatServiceBean();
        assertNotNull(service);
    }

    @Test
    public void testRegister() {
        final ChatService service = new ChatServiceBean();
        assertTrue(service.register("admin", "admin"));
        assertFalse(service.register("admin", "admin"));

        assertTrue(service.register("user-1", "user-1"));
        assertTrue(service.register("user-2", "user-2"));
        assertFalse(service.register("user-1", "user-1"));
        assertFalse(service.register("user-2", "user-2"));
    }

    @Test
    public void testSignIn() {
        final ChatService service = new ChatServiceBean();
        assertNull(service.signIn("admin", "admin"));
        service.register("admin", "admin");
        assertNotNull(service.signIn("admin", "admin"));
    }

    @Test
    public void testSignOut() {
        final ChatService service = new ChatServiceBean();
        service.register("admin", "admin");
        final Session sessionAdmin = service.signIn("admin", "admin");
        assertNotNull(sessionAdmin);
        assertNotNull(service.getUser(sessionAdmin));
        assertTrue(service.signOut(sessionAdmin));
        assertNull(service.getUser(sessionAdmin));
    }


    @Test
    public void testBroadCast() {
        final ChatService service = new ChatServiceBean();
        service.register("admin", "admin");
        service.register("user-1", "user-1");
        service.register("user-2", "user-2");
        final Session sessionAdmin = service.signIn("admin", "admin");
        final Session sessionUser1 = service.signIn("user-1", "user-1");
        final Session sessionUser2 = service.signIn("user-2", "user-2");

        service.sendBroadCast(sessionAdmin, "Ho-ho-ho!!!");
        assertTrue(service.getMessages(sessionAdmin).isEmpty()); //Sender didn't receive his own message

        assertFalse(service.getMessages(sessionUser1).isEmpty());
        assertTrue(service.getMessages(sessionUser1).isEmpty());

        assertFalse(service.getMessages(sessionUser2).isEmpty());
        assertTrue(service.getMessages(sessionUser2).isEmpty());
    }

    @Test
    public void testSendMessage() {
        final ChatService service = new ChatServiceBean();
        service.register("admin", "admin");
        service.register("user-1", "user-1");
        service.register("user-2", "user-2");

        final Session sessionAdmin = service.signIn("admin", "admin");
        assertTrue(service.sendMessage(sessionAdmin,"user-1", "User-1! Ho-ho-ho!!!"));
        assertTrue(service.sendMessage(sessionAdmin,"user-1", "User-1! again!!!"));
        assertTrue(service.sendMessage(sessionAdmin,"user-2", "User-2! Ho-ho-ho!!!"));
        assertFalse(service.sendMessage(sessionAdmin,"user-3", "User-3! Ho-ho-ho!!!"));

        final Session sessionUser1 = service.signIn("user-1", "user-1");
        assertTrue(service.getMessages(sessionUser1).size() == 2);
        assertTrue(service.getMessages(sessionUser1).isEmpty());

        final Session sessionUser2 = service.signIn("user-2", "user-2");
        assertTrue(service.getMessages(sessionUser2).size() == 1);
        assertTrue(service.getMessages(sessionUser2).isEmpty());
    }

    @Test
    public void testGetUserList() {
        final ChatService service = new ChatServiceBean();
        service.register("admin", "admin");
        service.register("user-1", "user-1");
        service.register("user-2", "user-2");

        final List<String> expected = new ArrayList<>();
        expected.add("admin");
        expected.add("user-1");
        expected.add("user-2");

        final List<String> actual = service.getListLogin();
        assertEquals(expected.size(), actual.size());
        for (String login: expected) {
            assertTrue(actual.contains(login));
            actual.remove(login);
        }
    }
}
