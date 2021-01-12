package org.netcracker.library.controller;

import org.netcracker.library.model.*;
import org.netcracker.library.util.RequestParser;

public class AddCommand extends Command {

    public AddCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public MessageInformation execute() {
        switch (key) {
            case "-t":
                return addTrack(args[0], args[1], args[2], args[3]);
            case "-a":
                return addAlbum(args[0], args[1]);
            case "-s":
                return addSinger(args[0]);
            default:
                return new MessageInformation(key, Code.INVALID_KEY);
        }
    }

    private MessageInformation addSinger(String name) {

        Singer singer = new Singer(name);

        if (!library.addSinger(singer))

            return new MessageInformation(null, null, name, Code.ADD_SINGER_ERROR);

        return new MessageInformation(null, null, name, Code.ADD_SINGER_TRUE);
    }

    private MessageInformation addAlbum(String albumName, String singerName) {
        Album album = new Album(albumName);

        if (library.getSingers().get(singerName) == null)
            return new MessageInformation(null, null, singerName, Code.SINGER_MISSING_FROM_LIBRARY);

        if (!library.getSingers().get(singerName).addAlbum(album))
            return new MessageInformation(null, albumName, singerName,Code.ADD_ALBUM_ERROR);

        return new MessageInformation(null, albumName, singerName, Code.ADD_ALBUM_TRUE);
    }

    private MessageInformation addTrack(String trackName, String length, String albumName, String singerName) {
        Track track = new Track(trackName, RequestParser.parseLength(length));

        if (library.getSingers().get(singerName) == null)
            return new MessageInformation(null, null, singerName, Code.SINGER_MISSING_FROM_LIBRARY);

        if (library.getSingers().get(singerName).getAlbums().get(albumName) == null)
            return new MessageInformation(null, albumName, null, Code.ALBUM_MISSING_FROM_LIBRARY);

        if (!library.getSingers().get(singerName).getAlbums().get(albumName).addTrack(track))
            return new MessageInformation(trackName, albumName, null, Code.ADD_TRACK_ERROR);

        return new MessageInformation(trackName, albumName, singerName, Code.ADD_TRACK_TRUE);
    }

}
