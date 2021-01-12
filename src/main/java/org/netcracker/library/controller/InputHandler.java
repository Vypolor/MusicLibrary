package org.netcracker.library.controller;

import org.netcracker.library.util.RequestParser;
import org.netcracker.library.view.OutputHandler;

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

        Command command = RequestParser.parseCommand(request);
        sendCode(command.execute(), new OutputHandler());
    }

    public void sendCode(ExecutionResult result, OutputHandler oh){
        oh.explainExecutionResult(result);
    }
}
