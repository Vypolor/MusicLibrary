package org.netcracker.library.view;

import org.netcracker.library.controller.InputHandler;

import java.util.HashMap;
import java.util.Map;

public class OutputHandler {

    public OutputHandler(){}

    private final Map<Integer, String> errors = new HashMap<>();

    {
        errors.put(0, "Successful!");

        errors.put(110, "This track is already in the album");
        errors.put(120, "This album is already in the library");
        errors.put(130, "This artist is already in the library");

        errors.put(210, "The specified track is not in the library");
        errors.put(220, "The specified album is not in the library");
        errors.put(230, "The specified artist is not in the library");

        errors.put(310, "The track with the new name is already on the album");
        errors.put(320, "The artist already has an album with the new name");
        errors.put(330, "The artist with the specified new name is already in the library");

        errors.put(404, "The path to the file is not found");

        errors.put(500, "Invalid value of the key");
    }

    public void errorHandler(int code){
            System.out.println(errors.get(code));
    }
}
