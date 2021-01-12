package org.netcracker.library.controller;

import org.netcracker.library.util.RequestParser;
import org.netcracker.library.view.OutputHandler;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class InputHandler {
    private Reader input;

    {
        input = new InputStreamReader(System.in);
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
