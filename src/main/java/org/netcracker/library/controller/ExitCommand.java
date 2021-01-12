package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public class ExitCommand extends Command{

    public ExitCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        System.exit(0);

        return new ExecutionResult(ResultCode.SUCCESS, name, key, args);
    }
}
