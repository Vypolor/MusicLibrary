package org.netcracker.library.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Library {

    private HashSet<Singer> singers = new HashSet<>();

    public Library(){
    }

    public Library(HashSet<Singer> singers) {
        this.singers = singers;
    }

    public void addSinger(Singer addSinger) throws Exception {
        if(singers.toArray().length == 0) {
            singers.add(addSinger);
        }
        else {
            Singer[] singerArr = new Singer[singers.toArray().length];
            singers.toArray(singerArr);
            for(int i = 0; i < singerArr.length; ++i){
                if(singerArr[i].getName().equals(addSinger.getName())){
                    throw new Exception("There is a performer with name: " + addSinger.getName() + "in the library" );
                }
                else
                {
                    singers.add(addSinger);
                }
            }
        }
    }

    public void editSinger(Singer editSinger, String newSingerName) throws Exception {
        //Запоминаем альбомы
        Set<Album> bucket = editSinger.getAlbums();

        //Создаём нового исполнителя
        Singer singer = new Singer(newSingerName);
        //Удаляем старого исполнителя
        deleteSinger(editSinger);
        //Устанавливаем ссылки старых альбомов на нового исполнителя
        Album[] albumsArr = new Album[bucket.toArray().length];
        bucket.toArray(albumsArr);
        for(int i = 0; i < albumsArr.length; ++i){
            albumsArr[i].setSinger(singer);
            singer.addAlbum(albumsArr[i]);
        }
        //Добавляем исполнителя
        addSinger(singer);
    }

    public void editSinger(Singer editSinger, Singer newSinger) throws Exception {
        deleteSinger(editSinger);
        addSinger(newSinger);
    }

    public void deleteSinger(Singer delSinger){
        if(singers.contains(delSinger)){
            singers.remove(delSinger);
        }
    }

    public HashSet<Singer> getSingers() {
        return singers;
    }

    public void setSingers(HashSet<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        String result = new String("org.netcracker.musiclibrary.entity.Library : \n");
        if(singers!= null) {
            for (Iterator<Singer> iterator = singers.iterator(); iterator.hasNext(); ) {
                result += iterator.next().toString();
            }
        }
        return result;
    }
}
