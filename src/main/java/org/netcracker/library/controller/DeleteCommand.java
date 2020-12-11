package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

import org.netcracker.library.model.Library;

import java.util.Map;

public class DeleteCommand extends Command {

    public DeleteCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public boolean execute() {
        switch (key){
            case "-t" :
                return deleteTrack(args[0], args[1], args[2]);
            case "-a":
                return deleteAlbum(args[0], args[1]);
            case "-s":
                return deleteSinger(args[0]);
        }
        return false;
    }

    private boolean deleteSinger(String name){

        Singer temp = new Singer(name);

        return library.deleteSinger(temp);

    }

    private boolean deleteAlbum(String albumName, String singerName){

        Album delete = library.getSingers().get(singerName)
                .getAlbums().get(albumName);

        return library
                .getSingers().get(singerName)
                .deleteAlbum(delete);

    }

    private boolean deleteTrack(String trackName, String albumName, String singerName) {

        Track delete = library.getSingers().get(singerName)
                .getAlbums().get(albumName)
                .getTracks().get(trackName);


        return library
                .getSingers().get(singerName)
                .getAlbums().get(albumName)
                .deleteTrack(delete);
    }

}
