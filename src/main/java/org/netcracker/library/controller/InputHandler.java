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

    public void readRequest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //BufferedReader in = new BufferedReader(input);

        Scanner in = new Scanner(System.in);
        String request = in.nextLine();

        Triple<String, String, String> res = RequestParser.parseCommand(request);
        invokeCommand(res);
    }

    public void invokeCommand(Triple<String, String, String> command) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        commands.get(command.getName())
                .getDeclaredConstructor(Library.class, String.class, String[].class)
                .newInstance(Library.getInstance(), command.getKey(), command.getArgs())
                .execute();
    }
}
