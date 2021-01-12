package org.netcracker.library.controller;

import org.netcracker.library.model.Code;
import org.netcracker.library.model.Library;

public class ExitCommand extends Command{

    protected ExitCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public MessageInformation execute() {
        System.exit(0);
        return new MessageInformation(Code.SUCCESSFUL);
    }
}
