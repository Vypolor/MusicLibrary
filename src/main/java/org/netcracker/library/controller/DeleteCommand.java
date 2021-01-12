package org.netcracker.library.controller;

import org.netcracker.library.model.*;

public class DeleteCommand extends Command {

    public DeleteCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public MessageInformation execute() {
        switch (key) {
            case "-t" :
                return deleteTrack(args[0], args[1], args[2]);
            case "-a":
                return deleteAlbum(args[0], args[1]);
            case "-s":
                return deleteSinger(args[0]);
            default:
                return new MessageInformation(key, Code.INVALID_KEY);
        }
    }

    private MessageInformation deleteSinger(String name){

        Singer delete = new Singer(name);

        if (!library.deleteSinger(delete))
            return new MessageInformation(null, null, name, Code.SINGER_MISSING_FROM_LIBRARY);

        return new MessageInformation(null, null, name, Code.DELETE_SINGER_TRUE);
    }

    private MessageInformation deleteAlbum(String albumName, String singerName){

        if (library.getSingers().get(singerName) == null)
            return new MessageInformation(null, null, singerName, Code.SINGER_MISSING_FROM_LIBRARY);

        Album delete = library.getSingers().get(singerName).getAlbums().get(albumName);

        if (!library.getSingers().get(singerName).deleteAlbum(delete))
            return new MessageInformation(null, albumName, null, Code.ALBUM_MISSING_FROM_LIBRARY);

        return new MessageInformation(null, albumName, singerName, Code.DELETE_ALBUM_TRUE);
    }

    private MessageInformation deleteTrack(String trackName, String albumName, String singerName) {

        if (library.getSingers().get(singerName) == null)
            return new MessageInformation(null, null, singerName, Code.SINGER_MISSING_FROM_LIBRARY);

        if (library.getSingers().get(singerName).getAlbums().get(albumName) == null)
            return new MessageInformation(null, albumName, null, Code.ALBUM_MISSING_FROM_LIBRARY);

        Track delete = library.getSingers().get(singerName).getAlbums().get(albumName).getTracks().get(trackName);

        if (!library.getSingers().get(singerName).getAlbums().get(albumName).deleteTrack(delete))
            return new MessageInformation(trackName, albumName, null, Code.TRACK_MISSING_FROM_LIBRARY);

        return new MessageInformation(trackName, albumName, singerName, Code.DELETE_TRACK_TRUE);
    }

}
