package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;
import org.netcracker.library.util.RequestParser;

public class AddCommand extends Command {

    public AddCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        ResultCode resultCode;

        switch (key) {
            case "-t":
                resultCode = addTrack(args[0], args[1], args[2], args[3]);
            case "-a":
                resultCode = addAlbum(args[0], args[1]);
            case "-s":
                resultCode = addSinger(args[0]);
            default:
                resultCode = ResultCode.INVALID_KEY;
        }

        return new ExecutionResult(resultCode, name, key, args);
    }

    private ResultCode addSinger(String name) {
        Singer singer = new Singer(name);

        if (!library.addSinger(singer))
            return ResultCode.ITEM_EXISTS;

        return ResultCode.SUCCESS;
    }

    private ResultCode addAlbum(String albumName, String singerName) {
        Album album = new Album(albumName);

        if (library.getSingers().get(singerName) == null)
            return ResultCode.ITEM_NOT_EXISTS;

        if (!library.getSingers().get(singerName).addAlbum(album))
            return ResultCode.ITEM_EXISTS;

        return ResultCode.SUCCESS;
    }

    private ResultCode addTrack(String trackName, String length, String albumName, String singerName) {
        Track track = new Track(trackName, RequestParser.parseLength(length));

        if (library.getSingers().get(singerName) == null)
            return ResultCode.ITEM_NOT_EXISTS;

        if (library.getSingers().get(singerName)
                .getAlbums().get(albumName) == null)
            return ResultCode.ITEM_NOT_EXISTS;

        if (!library.getSingers().get(singerName)
                .getAlbums().get(albumName)
                .addTrack(track))
            return ResultCode.ITEM_EXISTS;

        return ResultCode.SUCCESS;
    }

}
