package org.netcracker.library.model;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private static Library instance = null;

    private String name;
    private Map<String, Singer> singers = new HashMap<>();

    public Library() {
    }

    private Library(String name, Map<String, Singer> singers) {
        this.name = name;
        this.singers = singers;
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }

        return instance;
    }

    public static void setAll(String name, Map<String, Singer> singers) {
        getInstance().setName(name);
        getInstance().setSingers(singers);
    }

    public boolean addSinger(Singer addSinger) {
        Singer prev = singers.put(addSinger.getName(), addSinger);

        return !addSinger.equals(prev);
    }

    public boolean editSinger(Singer oldSinger, Singer newSinger) {
        if (deleteSinger(oldSinger))
            return addSinger(newSinger);

        return false;
    }

    public boolean deleteSinger(Singer delSinger){
        Singer removed = singers.remove(delSinger.getName());

        return delSinger.equals(removed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Singer> getSingers() {
        return singers;
    }

    public void setSingers(Map<String, Singer> singers) {
        this.singers = singers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(name, library.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder singersList = new StringBuilder("Library " + getName() + ": \n");

        for (Singer singer : singers.values()) {
            singersList.append(singer.toString());
        }

        return singersList.toString();
    }
}
