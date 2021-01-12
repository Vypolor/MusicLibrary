package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public class HelpCommand extends Command{

    public HelpCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        System.out.println("========================\n\tList of commands\n========================");

        printCommandList();

        return new ExecutionResult(ResultCode.SUCCESS, name, key, args);
    }

    public void printCommandList(){

        String add = "\n1. /add" +
                "\nthe possible commands:" +
                "\n---------------\n" +
                "Adding a track: /add -t \"track name\" \"track length\" \"album name\" \"singer name\" \n"+
                "Adding a album: /add -a \"album name\" \"singer name\" \n"+
                "Adding a singer:/add -s \"singer name\"";
        String delete = "\n2. /delete"+
                "\nthe possible commands:" +
                "\n---------------\n" +
                "Deleting a track: /delete -t \"track name\" \"album name\" \"singer name\" \n"+
                "Deleting a album: /delete -a \"album name\" \"singer name\" \n"+
                "Deleting a singer:/delete -s \"singer name\"";
        String search = "\n3. /search\n"+
                "Searching a track: /search \"track name\" also you can use \"*\" and \"?\"";
        String show = "\n4. /show"+
                "shows the entire library: /show";
        String exit = "\n5. /exit"+
                "exit the program: /exit";
        System.out.println(add);
        System.out.println(delete);
        System.out.println(search);
        System.out.println(show);
        System.out.println(exit);
    }
}
