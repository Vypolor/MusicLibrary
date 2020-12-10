package org.netcracker.library.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    public static Triple<String, String, String> parseCommand(String request) {
        Pattern cmdNamePattern = Pattern.compile("(/[a-z]+)");
        Matcher cmdNameMatcher = cmdNamePattern.matcher(request);

        Pattern keyPattern = Pattern.compile("(-[a-z])");
        Matcher keyMatcher = keyPattern.matcher(request);

        Pattern argsPattern = Pattern.compile("\"([a-zA-Zа-яА-ЯёЁ\\s]+)\"");
        Matcher argsMatcher = argsPattern.matcher(request);

        String cmdName = null;
        String key = null;
        String[] args = new String[4];

        if (cmdNameMatcher.find())
            cmdName = cmdNameMatcher.group(1);

        if (keyMatcher.find())
            key = keyMatcher.group(1);

        int counter = 0;
        while (argsMatcher.find()) {
            args[counter] = argsMatcher.group(1);
            counter++;
        }

        return new Triple<>(cmdName, key, args);
    }

    public static long parseLength(String length) {
        //length pattern hh:mm:ss
        String[] splitted = length.split(":");

        return Long.parseLong(splitted[0]) * 3600000
                + Long.parseLong(splitted[1]) * 60000
                + Long.parseLong(splitted[2]) * 1000;
    }

}