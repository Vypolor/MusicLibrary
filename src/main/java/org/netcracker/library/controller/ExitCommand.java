package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public class ExitCommand extends Command{
    protected ExitCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public int execute() {
        System.exit(0);
        return 0;
    }
}
