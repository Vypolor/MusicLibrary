package org.netcracker.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement(name = "singer")
public class Singer implements Serializable {

    private String name;
    private Map<String, Album> albums = new HashMap<>();

    public Singer() {
    }

    public Singer(String name) {
        this.name = name;
    }

    //+
    public Singer(String name, HashMap<String, Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public boolean addAlbum(Album addAlbum) {
        addAlbum.setSinger(this);

        Album prev = albums.put(addAlbum.getName(), addAlbum);

        return !addAlbum.equals(prev);
    }

    public boolean editAlbum(Album oldAlbum, Album newAlbum) {
        if (deleteAlbum(oldAlbum))
            return addAlbum(newAlbum);

        return false;
    }

    public boolean deleteAlbum(Album delAlbum) {
        delAlbum.setSinger(null);

        Album removed = albums.remove(delAlbum.getName());

        return delAlbum.equals(removed);
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Album> getAlbums() {
        return albums;
    }

    @XmlElement(name = "albums")
    public void setAlbums(Map<String, Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Singer singer = (Singer) o;
        return Objects.equals(name, singer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder albumsList = new StringBuilder();

        for (Album album : albums.values()) {
            albumsList.append(album.toString());
        }

        return "=================================="+"\nSinger: " + getName()
                + albumsList.toString();
    }
}
