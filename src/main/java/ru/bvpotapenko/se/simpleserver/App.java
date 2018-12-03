package ru.bvpotapenko.se.simpleserver;

import ru.bvpotapenko.se.simpleserver.client.ChatClient;
import ru.bvpotapenko.se.simpleserver.server.ChatServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static final Map<String, Class<?>> ENTRY_POINTS =
            new HashMap<String, Class<?>>();
    static{
        ENTRY_POINTS.put("-startserver", ChatServer.class);
        ENTRY_POINTS.put("-startclient", ChatClient.class);
    }
    public static void main( String[] args ) throws Exception{

        if (args.length < 1){
            System.out.println("-startserver or -startclient required");
            System.exit(0);
        }
        final Class<?> entryPoint = ENTRY_POINTS.get(args[0]);
        if (entryPoint == null){
            System.out.println("-startserver or -startclient expected");
            System.exit(0);
        }
        final String[] argsCopy =
                args.length > 1
                        ? Arrays.copyOfRange(args, 1, args.length)
                        : new String[0];
        entryPoint.getMethod("main", String[].class).invoke(null,
                (Object) argsCopy);
    }
}
