package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public class ShowCommand extends Command {

    public ShowCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        show();

        return new ExecutionResult(ResultCode.SUCCESS, name, key, args);
    }

    private void show() {
        System.out.println(library.toString());
    }

}
