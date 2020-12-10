package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

public class AddCommand extends Command {

    public AddCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public boolean execute() {
        switch (key) {
            case "-t":
                return addTrack(args[0], args[1], args[2], args[3]);
            case "-a":
                return addAlbum(args[0], args[1]);
            case "-s":
                return addSinger(args[0]);
        }

        return false;
    }

    private boolean addSinger(String name) {
        Singer singer = new Singer(name);

        return library.addSinger(singer);
    }

    private boolean addAlbum(String albumName, String singerName) {
        Album album = new Album(albumName);

        return library
                .getSingers().get(singerName)
                .addAlbum(album);
    }

    private boolean addTrack(String trackName, String length, String albumName, String singerName) {
        Track track = new Track(trackName, Long.parseLong(length));

        //Map.get() needs to be handled
        return library
                .getSingers().get(singerName)
                .getAlbums().get(albumName)
                .addTrack(track);
    }

}
