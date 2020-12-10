package org.netcracker.library.model;

import java.io.Serializable;
import java.util.*;

public class Album implements Serializable {

    private String name;
    private Map<String, Track> tracks = new HashMap<>();
    private Singer singer;

    public Album(String name) {
        this.name = name;
    }

    //+
    public Album(String name, Singer singer) {
        this.name = name;
        this.singer = singer;
    }

    //Для загрузки библиотеки (может ошибаюсь) +
    public Album(String name, HashMap<String, Track> tracks, Singer singer) {
        this.name = name;
        this.tracks = tracks;
        this.singer = singer;
    }

    //?
    public boolean addTrack(Track addTrack) {
        addTrack.setAlbum(this);

        Track prev = tracks.put(addTrack.getName(), addTrack);

        //return true if addTrack != previous associated Track
        return !addTrack.equals(prev);
    }

    //Обычное изменение трека
    public boolean editTrack(Track oldTrack, Track newTrack) {
        if (deleteTrack(oldTrack))
            return addTrack(newTrack);

        return false;
    }

    public boolean deleteTrack(Track delTrack){
        delTrack.setAlbum(null);

        Track removed = tracks.remove(delTrack.getName());

        //return true if delTrack = previous associated Track
        return delTrack.equals(removed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Track> getTracks() {
        return tracks;
    }

    public void setTracks(Map<String, Track> tracks) {
        this.tracks = tracks;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder tracksList = new StringBuilder("\n");

        for (Track track : tracks.values()) {
            tracksList.append(track.toString());
        }

        return "\n\t=============================="+"\n\tAlbum Name: " + getName()
                + tracksList.toString()
                + "\n";
    }

}
