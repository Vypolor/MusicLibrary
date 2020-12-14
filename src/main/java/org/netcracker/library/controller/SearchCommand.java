package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCommand extends Command {

    protected SearchCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public int execute() {
        if((args[0].contains("*") || args[0].contains("?")) ){
            return patternSearch(args[0]);
        }
        else
            simpleSearch(args[0]);
        return 500;
    }

    public int patternSearch(String searchString){

        String strPattern =
                "^" + searchString
                .replaceAll("\\?", ".")
                .replaceAll("\\*", ".*") + "$";

        Pattern p = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);

        Matcher m;

        int count = 0;

        for(Singer singer : library.getSingers().values()){
            for(Album album: singer.getAlbums().values()){
                for(Track track: album.getTracks().values()){
                    m = p.matcher(track.getName());
                    if(m.find()){
                        System.out.println(singer.getName() + " | " + album.getName() + " | " + track.getName());
                        ++count;
                    }
                }
            }
        }

        System.out.println("Number of matches: " + count);
        return 0;
    }

    public int simpleSearch(String searchString){

        int count = 0;
        Track track;

        for(Singer singer: library.getSingers().values()){
            for(Album album: singer.getAlbums().values()){
                if((track = album.getTracks().get(searchString)) != null){
                    System.out.println(singer.getName() + " | " + album.getName() + " | " + track.getName());
                    ++count;
                }
            }
        }

        System.out.println("Number of matches: " + count);
        return 0;
    }
}
