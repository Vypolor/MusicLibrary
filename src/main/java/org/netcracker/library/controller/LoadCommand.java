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
import java.util.Map;
import java.util.stream.Collectors;

public class LoadCommand extends Command {
    public LoadCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public int execute() {
        return 0;
    }

    private int loadFromFile(String path) throws JAXBException {
        Library newInstance = readLibrary(path);

        Library currentInstance = library;

        currentInstance.setName(newInstance.getName());
        currentInstance.setSingers(newInstance.getSingers());

        return 0;
    }

    private int loadFromFileMerged(String path) throws JAXBException {
        Library newInstance = readLibrary(path);

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
                                albumTracks.put(track.getName(), track);
                        }
                    }
                }
            }
        }

        return 0;
    }

    private Library readLibrary(String path) throws JAXBException {
        File file = new File(path);

        JAXBContext context = JAXBContext.newInstance(Library.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Library) unmarshaller.unmarshal(file);
    }
}
