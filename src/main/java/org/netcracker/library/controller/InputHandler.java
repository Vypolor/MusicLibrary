package org.netcracker.library.controller;

import org.netcracker.library.model.Library;
import org.netcracker.library.util.RequestParser;
import org.netcracker.library.util.Triple;
import org.netcracker.library.view.OutputHandler;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputHandler {
    private Reader input;
    private static int code;
    private final Map<String, Class<? extends Command>> commands = new HashMap<>();

    {
        input = new InputStreamReader(System.in);

        commands.put("/?", HelpCommand.class);
        commands.put("/add", AddCommand.class);
        commands.put("/delete", DeleteCommand.class);
        commands.put("/exit", ExitCommand.class);
        commands.put("/search", SearchCommand.class);
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
        sendCode(invokeCommand(res).execute(), new OutputHandler());
    }

    private Command invokeCommand(Triple<String, String, String> command) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return commands.get(command.getName())
                .getDeclaredConstructor(Library.class, String.class, String[].class)
                .newInstance(Library.getInstance(), command.getKey(), command.getArgs());
    }

    public void sendCode(int code, OutputHandler oh){
        oh.errorHandler(code);
    }
}
