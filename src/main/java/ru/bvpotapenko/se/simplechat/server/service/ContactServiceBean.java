package ru.bvpotapenko.se.simplechat.server.service;

import ru.bvpotapenko.se.simplechat.server.api.ContactService;
import ru.bvpotapenko.se.simplechat.server.model.Contact;
import ru.bvpotapenko.se.simplechat.server.model.ContactBox;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactServiceBean implements ContactService {

    private final Map<String, ContactBox> contacts = new LinkedHashMap<>();

    @Override
    public ContactBox getContacts(String login) {
        if (login == null || login.isEmpty()) return null;
        if (!contacts.containsKey(login)) contacts.put(login, new ContactBox());
        return contacts.get(login);
    }

    @Override
    public Contact addContact(String source, String target) {
        if (source == null || source.isEmpty()) return null;
        if (target == null || target.isEmpty()) return null;
        final Contact contact = new Contact();
        contact.source = source;
        contact.target = target;
        contacts.get(source).add(contact);
        return contact;
    }

    @Override
    public boolean removeContact(String source, String target) {
        if (source == null || source.isEmpty()) return false;
        if (target == null || target.isEmpty()) return false;
        ContactBox contactBox = getContacts(source);
        return contactBox.removeIf(e-> e.source.equals(source) && e.target.equals(target));
    }

    @Override
    public boolean removeAllContacts(String login) {
        if (login == null || login.isEmpty()) return false;
        contacts.put(login, new ContactBox());
        return true;
    }
}
