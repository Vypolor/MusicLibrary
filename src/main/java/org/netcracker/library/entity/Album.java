package org.netcracker.library.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Album {

    private String name;
    private Set<Track> tracks = new HashSet<>();
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
    public Album(String name, HashSet<Track> tracks, Singer singer) {
        this.name = name;
        this.tracks = tracks;
        this.singer = singer;
    }

    //?
    public boolean addTrack(Track addTrack) {
        addTrack.setAlbum(this);

        return tracks.add(addTrack);
    }

    //Обычное изменение трека
    public boolean editTrack(Track oldTrack, Track newTrack) {
        if (!deleteTrack(oldTrack))
            return false;

        return addTrack(newTrack);
    }

    public boolean deleteTrack(Track delTrack){
        delTrack.setAlbum(null);

        return tracks.remove(delTrack);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
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

        for (Track track : tracks) {
            tracksList.append(track.toString());
        }

        return "\n\t=============================="+"\n\tAlbum Name: " + getName()
                + tracksList.toString()
                + "\n";
    }

}
