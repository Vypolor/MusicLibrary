package org.netcracker.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "track")
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

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public void setAlbum(Album album) {
        this.album = album;
    }

    @XmlAttribute(name = "length")
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
