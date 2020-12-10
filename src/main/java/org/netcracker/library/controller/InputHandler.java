package org.netcracker.library.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

    public void readRequest() throws IOException {
        BufferedReader in = new BufferedReader(input);

        String request = in.readLine();
    }
}
