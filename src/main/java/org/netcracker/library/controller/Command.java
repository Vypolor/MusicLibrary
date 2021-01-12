package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public abstract class Command {
    protected final Library library;
    protected final String name;
    protected final String key;
    protected final String[] args;

    protected Command(Library library, String name, String key, String[] args) {
        this.library = library;
        this.name = name;
        this.key = key;
        this.args = args;
    }

    public abstract ExecutionResult execute();

}
