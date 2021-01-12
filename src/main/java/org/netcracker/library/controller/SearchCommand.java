package org.netcracker.library.controller;

import org.netcracker.library.model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCommand extends Command {

    protected SearchCommand(Library library, String key, String[] args) {
        super(library, key, args);
    }

    @Override
    public MessageInformation execute() {
        if((args[0].contains("*") || args[0].contains("?")) ){
            return patternSearch(args[0]);
        }
        else
            return simpleSearch(args[0]);
    }

    public MessageInformation patternSearch(String searchString){

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

        System.out.println("==============================" + count);
        System.out.println("Number of matches: " + count);
        System.out.println("==============================" + count);
        if(count == 0 ){
            return new MessageInformation(Code.EMPTY_SEARCH);
        }
        else
            return new MessageInformation(Code.NOT_EMPTY_SEARCH);
    }

    public MessageInformation simpleSearch(String searchString){

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

        System.out.println("==============================" + count);
        System.out.println("Number of matches: " + count);
        System.out.println("==============================" + count);
        if(count == 0 ){
            return new MessageInformation(Code.EMPTY_SEARCH);
        }
        else
            return new MessageInformation(Code.NOT_EMPTY_SEARCH);
    }
}
