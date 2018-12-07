package ru.bvpotapenko.se.simplechat.server.model;

import java.util.Objects;

public class Contact {
    public String source;
    public String target;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return source.equals(contact.source) &&
                target.equals(contact.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
