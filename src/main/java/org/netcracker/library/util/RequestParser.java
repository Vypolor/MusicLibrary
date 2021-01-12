package org.netcracker.library.util;

import org.netcracker.library.controller.*;
import org.netcracker.library.model.Library;

import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    private static final Map<String, Class<? extends Command>> commands = new HashMap<>();

    static {
        commands.put("/?", HelpCommand.class);
        commands.put("/add", AddCommand.class);
        commands.put("/delete", DeleteCommand.class);
        commands.put("/exit", ExitCommand.class);
        commands.put("/search", SearchCommand.class);
        commands.put("/show", ShowCommand.class);
        commands.put("/save", SaveCommand.class);
        commands.put("/load", LoadCommand.class);
    }

    public static Command parseCommand(String request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String cmdName = null;
        String key = null;
        String[] args = new String[4];

        Pattern cmdNamePattern = Pattern.compile("(/[a-z]+)");
        Matcher cmdNameMatcher = cmdNamePattern.matcher(request);

        Pattern keyPattern = Pattern.compile("(-[a-z])");
        Matcher keyMatcher = keyPattern.matcher(request);

        Pattern argsPattern = Pattern.compile("\"([a-zA-Zа-яА-ЯёЁ0-9:*?\\s]+)\"");
        Matcher argsMatcher = argsPattern.matcher(request);

        if (cmdNameMatcher.find())
            cmdName = cmdNameMatcher.group(1);

        if (keyMatcher.find())
            key = keyMatcher.group(1);

        for (int i = 0; i < 4; i++) {
            if (argsMatcher.find()) {
                args[i] = argsMatcher.group(1);
            }
        }

        return commands.get(cmdName)
                .getDeclaredConstructor(Library.class, String.class, String.class, String[].class)
                .newInstance(Library.getInstance(), cmdName, key, args);
    }

    public static long parseLength(String length) {
        //length pattern hh:mm:ss
        String[] splitted = length.split(":");

        return Long.parseLong(splitted[0]) * 3600000
                + Long.parseLong(splitted[1]) * 60000
                + Long.parseLong(splitted[2]) * 1000;
    }

}
