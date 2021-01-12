package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class LoadCommand extends Command {

    public LoadCommand(Library library, String name, String key, String[] args) {
        super(library, name, key, args);
    }

    @Override
    public ExecutionResult execute() {
        ResultCode resultCode;

        switch (key) {
            case "-r":
                try {
                    resultCode = loadFromFileReplacing(args[0]);
                } catch (JAXBException e) {
                    resultCode = ResultCode.UNEXPECTED_SAVE_ERROR;
                }
            case "-d", "-n", "-c":
                try {
                    resultCode = loadFromFileMerging(args[0], key);
                } catch (JAXBException e) {
                    resultCode = ResultCode.UNEXPECTED_SAVE_ERROR;
                }
            default:
                resultCode = ResultCode.INVALID_KEY;
        }

        return new ExecutionResult(resultCode, name, key, args);
    }

    private ResultCode loadFromFileReplacing(String path) throws JAXBException {
        Library newInstance;

        try {
            newInstance = readLibrary(path);
        } catch (InvalidPathException e) {
            return ResultCode.INVALID_PATH;
        }

        Library currentInstance = library;

        currentInstance.setName(newInstance.getName());
        currentInstance.setSingers(newInstance.getSingers());

        return ResultCode.SUCCESS;
    }

    private ResultCode loadFromFileMerging(String path, String key) throws JAXBException {
        //possible key values: -d (default merge), -c (merge saving current), -n (merge saving new)
        Library newInstance;

        try {
            newInstance = readLibrary(path);
        } catch (InvalidPathException e) {
            return ResultCode.INVALID_PATH;
        }

        for (Singer singer : newInstance.getSingers().values()) {
            Map<String, Singer> currentSingers = library.getSingers();
            if (!currentSingers.containsKey(singer.getName()))
                currentSingers.put(singer.getName(), singer);
            else {
                Map<String, Album> singerAlbums = currentSingers.get(singer.getName()).getAlbums();
                for (Album album : newInstance.getSingers().get(singer.getName()).getAlbums().values()) {
                    if (!singerAlbums.containsKey(album.getName()))
                        singerAlbums.put(album.getName(), album);
                    else {
                        Map<String, Track> albumTracks = singerAlbums.get(album.getName()).getTracks();
                        for (Track track : newInstance.getSingers().get(singer.getName()).getAlbums().get(album.getName()).getTracks().values()) {
                            if (!albumTracks.containsKey(track.getName()))
                                //independent of key value
                                albumTracks.put(track.getName(), track);
                            else
                                if (key.equals("-n"))
                                    albumTracks.replace(track.getName(), track);
                        }
                    }
                }
            }
        }

        return ResultCode.SUCCESS;
    }

    private Library readLibrary(String path) throws JAXBException {
        File file = Paths.get(path).toFile();

        JAXBContext context = JAXBContext.newInstance(Library.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Library) unmarshaller.unmarshal(file);
    }
}
