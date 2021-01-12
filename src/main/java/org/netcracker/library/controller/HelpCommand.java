package org.netcracker.library.controller;

import org.netcracker.library.model.Code;
import org.netcracker.library.model.Library;

public class HelpCommand extends Command{

    protected HelpCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public MessageInformation execute() {
        System.out.println("========================\n\tList of commands\n========================");
        printCommandList();
        return new MessageInformation(Code.SUCCESSFUL);
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
        String show = "\n4. /show "+
                "shows the entire library: /show";
        String exit = "\n5. /exit "+
                "exit the program: /exit";
        System.out.println(add);
        System.out.println(delete);
        System.out.println(search);
        System.out.println(show);
        System.out.println(exit);
    }
}
