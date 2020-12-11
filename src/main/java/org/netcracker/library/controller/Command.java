package org.netcracker.library.controller;

import org.netcracker.library.model.Library;

public abstract class Command {
    protected final Library library;
    protected final String key;
    protected final String[] args;

    protected Command(Library library, String key, String[] args) {
        this.library = library;
        this.key = key;
        this.args = args;
    }

    public abstract int execute();

}
