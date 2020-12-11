package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;
import org.netcracker.library.util.RequestParser;

public class AddCommand extends Command {

    public AddCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public int execute() {
        switch (key) {
            case "-t":
                return addTrack(args[0], args[1], args[2], args[3]);
            case "-a":
                return addAlbum(args[0], args[1]);
            case "-s":
                return addSinger(args[0]);
            default:
                return 500;
        }
    }

    private int addSinger(String name) {
        Singer singer = new Singer(name);

        if (!library.addSinger(singer))
            return 130;

        return 0;
    }

    private int addAlbum(String albumName, String singerName) {
        Album album = new Album(albumName);

        if (library.getSingers().get(singerName) == null)
            return 230;

        if (!library.getSingers().get(singerName).addAlbum(album))
            return 120;

        return 0;
    }

    private int addTrack(String trackName, String length, String albumName, String singerName) {
        Track track = new Track(trackName, RequestParser.parseLength(length));

        if (library.getSingers().get(singerName) == null)
            return 230;

        if (library.getSingers().get(singerName).getAlbums().get(albumName) == null)
            return 220;

        if (!library.getSingers().get(singerName).getAlbums().get(albumName).addTrack(track))
            return 110;

        return 0;
    }

}
