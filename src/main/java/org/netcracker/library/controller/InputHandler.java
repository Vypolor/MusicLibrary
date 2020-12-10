package org.netcracker.library.controller;

import org.netcracker.library.model.Library;
import org.netcracker.library.util.RequestParser;
import org.netcracker.library.util.Triple;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputHandler {
    private Reader input;
    private final Map<String, Class<? extends Command>> commands = new HashMap<>();

    {
        input = new InputStreamReader(System.in);

        commands.put("/add", AddCommand.class);
        commands.put("/delete", DeleteCommand.class);
        commands.put("/show", ShowCommand.class);
    }

    public InputHandler() {
    }

    public InputHandler(Reader input) {
        this.input = input;
    }

    public Triple<String, String, String> readRequest() throws IOException {
        BufferedReader in = new BufferedReader(input);

        String request = in.readLine();

        return RequestParser.parseCommand(request);
    }

    public void invokeCommand(Triple<String, String, String> command) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        commands.get(command.getName())
                .getDeclaredConstructor(Library.class, String.class, String[].class)
                .newInstance(Library.getInstance(), command.getKey(), command.getArgs())
                .execute();
    }
}
