package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public class ShowCommand extends Command {

    public ShowCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public boolean execute() {
        show();

        return true;
    }

    private void show() {
        System.out.println(library.toString());
    }

}
