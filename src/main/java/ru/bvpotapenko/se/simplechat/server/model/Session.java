package ru.bvpotapenko.se.simplechat.server.model;

import java.util.*;

public final class Session {
    public Long timestamp = System.currentTimeMillis();

    public String id = UUID.randomUUID().toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Session))
            return false;
        Session session = (Session) o;
        final boolean checkTimestamp = Objects.equals(timestamp, session.timestamp);
        final boolean checkSessionId = Objects.equals(id, session.id);
        return checkTimestamp && checkSessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, id);
    }
}
