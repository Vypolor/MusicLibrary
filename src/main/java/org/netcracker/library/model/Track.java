package org.netcracker.library.model;

import java.io.Serializable;
import java.util.Objects;

public class Track implements Serializable {

    private String name;
    private Album album;
    private long time;

    public Track() {
    }

    public Track(String name, long time) {
        this.name = name;
        this.time = time;
    }

    //+
    public Track(String name, Album album, long time) {
        this.name = name;
        this.album = album;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Album getAlbum() {
        return album;
    }

    public long getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(name, track.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return  "\t\t=========================="
                + "\n\t\tTrack Name: " + getName()
                + "\n\t\tTrack Length: " + getTime()
                + "\n";
    }
}
