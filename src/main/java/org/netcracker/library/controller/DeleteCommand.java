package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

public class DeleteCommand extends Command {

    public DeleteCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        ResultCode resultCode;

        switch (key) {
            case "-t" :
                resultCode = deleteTrack(args[0], args[1], args[2]);
            case "-a":
                resultCode = deleteAlbum(args[0], args[1]);
            case "-s":
                resultCode = deleteSinger(args[0]);
            default:
                resultCode = ResultCode.INVALID_KEY;
        }

        return new ExecutionResult(resultCode, name, key, args);
    }

    private ResultCode deleteSinger(String name){

        Singer delete = new Singer(name);

        if (!library.deleteSinger(delete))
            return ResultCode.ITEM_NOT_EXISTS;

        return ResultCode.SUCCESS;
    }

    private ResultCode deleteAlbum(String albumName, String singerName){

        if (library.getSingers().get(singerName) == null)
            return ResultCode.ITEM_NOT_EXISTS;

        Album delete = library.getSingers().get(singerName).getAlbums().get(albumName);

        if (!library.getSingers().get(singerName).deleteAlbum(delete))
            return ResultCode.ITEM_NOT_EXISTS;

        return ResultCode.SUCCESS;
    }

    private ResultCode deleteTrack(String trackName, String albumName, String singerName) {

        if (library.getSingers().get(singerName) == null)
            return ResultCode.ITEM_NOT_EXISTS;

        if (library.getSingers().get(singerName).getAlbums().get(albumName) == null)
            return ResultCode.ITEM_NOT_EXISTS;

        Track delete = library.getSingers().get(singerName).getAlbums().get(albumName).getTracks().get(trackName);

        if (!library.getSingers().get(singerName).getAlbums().get(albumName).deleteTrack(delete))
            return ResultCode.ITEM_NOT_EXISTS;

        return ResultCode.SUCCESS;
    }

}
