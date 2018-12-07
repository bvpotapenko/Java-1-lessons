package ru.bvpotapenko.se.simplechat.server.api;

import ru.bvpotapenko.se.simplechat.server.model.Contact;
import ru.bvpotapenko.se.simplechat.server.model.ContactBox;

public interface ContactService {
    ContactBox getContacts (String login);
    Contact addContact(String source, String target);

    boolean removeContact(String source, String target);
    boolean removeAllContacts(String source);
}
