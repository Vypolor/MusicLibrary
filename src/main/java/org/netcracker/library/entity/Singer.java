package org.netcracker.library.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Singer {

    private String name;
    private Set<Album> albums = new HashSet<>();

    public Singer(String name) {
        this.name = name;
    }

    //+
    public Singer(String name, HashSet<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public boolean addAlbum(Album addAlbum) {
        addAlbum.setSinger(this);

        return albums.add(addAlbum);
    }

    //Для замены одного альбома другим
    public boolean editAlbum(Album oldAlbum, Album newAlbum) {
        if (!albums.contains(oldAlbum))
            return false;

        if (!albums.contains(newAlbum)) {
            oldAlbum.setName(newAlbum.getName());
            return true;
        }

        return false;
    }

    public boolean deleteAlbum(Album delAlbum) {
        delAlbum.setSinger(null);

        return albums.remove(delAlbum);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
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

        for (Album album : albums) {
            albumsList.append(album.toString());
        }

        return "=================================="+"\nSinger: " + getName()
                + albumsList.toString();
    }
}
