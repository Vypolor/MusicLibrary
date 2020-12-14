package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

public class DeleteCommand extends Command {

    public DeleteCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public int execute() {
        switch (key) {
            case "-t" :
                return deleteTrack(args[0], args[1], args[2]);
            case "-a":
                return deleteAlbum(args[0], args[1]);
            case "-s":
                return deleteSinger(args[0]);
            default:
                return 500;
        }
    }

    private int deleteSinger(String name){

        Singer delete = new Singer(name);

        if (!library.deleteSinger(delete))
            return 230;

        return 0;
    }

    private int deleteAlbum(String albumName, String singerName){

        if (library.getSingers().get(singerName) == null)
            return 230;

        Album delete = library.getSingers().get(singerName).getAlbums().get(albumName);

        if (!library.getSingers().get(singerName).deleteAlbum(delete))
            return 220;

        return 0;
    }

    private int deleteTrack(String trackName, String albumName, String singerName) {

        if (library.getSingers().get(singerName) == null)
            return 230;

        if (library.getSingers().get(singerName).getAlbums().get(albumName) == null)
            return 220;

        Track delete = library.getSingers().get(singerName).getAlbums().get(albumName).getTracks().get(trackName);

        if (!library.getSingers().get(singerName).getAlbums().get(albumName).deleteTrack(delete))
            return 210;

        return 0;
    }

}
