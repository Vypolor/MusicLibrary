import java.util.HashSet;
import java.util.Iterator;

public class Album {

    private String name;
    private HashSet<Track> tracks = new HashSet<Track>();
    private Singer singer;

    public Album(String name) {
        this.name = name;
    }

    public Album(String name, Singer singer) {
        this.name = name;
        this.singer = singer;
    }

    //Для загрузки библиотеки (может ошибаюсь)
    public Album(String name, HashSet<Track> tracks, Singer singer) {
        this.name = name;
        this.tracks = tracks;
        this.singer = singer;
    }

    public void addTrack(Track addTrack) throws Exception {
        //Если в альбоме нет треков то добавление будет 100%
        if(tracks.toArray().length == 0){
            tracks.add(addTrack);
        }
        //В случае если в альбоме уже есть треки то необходима проверка
        //которая будет сравнивать названия уже имеющихся треков с названием трека, котороый мы хотим добавить
        else {
            Track [] trackArr = new Track[tracks.toArray().length];
            tracks.toArray(trackArr);
            for (int i = 0; i < trackArr.length; ++i){
                if(trackArr[i].getName().equals(addTrack.getName())){

                    //Если трек с таким названием уже есть в альбоме выбрасывается исключение
                    throw new Exception("track with name: " + addTrack.getName() + " is already in the album");
                }
                else {
                    addTrack.setAlbum(this);
                    tracks.add(addTrack);
                }
            }
        }
    }

    //Для замены одного трека другим
    public void editTrack(Track editTrack, Track newTrack) throws Exception {
        if(!(tracks.contains(newTrack))) {
            deleteTrack(editTrack);
            addTrack(newTrack);
        }
    }

    //Обычное изменение трека
    public void editTrack(Track editTrack, String newTrackName, long time){
        deleteTrack(editTrack);
        Track track = new Track(newTrackName, time);
        tracks.add(track);
    }

    public void deleteTrack(Track delTrack){
        if(tracks.contains(delTrack)){
            delTrack.setAlbum(null);
            tracks.remove(delTrack);
        }
    }

    public String getName() {
        return name;
    }

    public HashSet<Track> getTracks() {
        return tracks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTracks(HashSet<Track> tracks) {
        this.tracks = tracks;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        String result = new String( "\n\t=============================="+"\n\tAlbum Name: " + getName() + "\n");
        if(tracks != null) {
            for (Iterator<Track> iterator = tracks.iterator(); iterator.hasNext(); ) {
                result += iterator.next().toString();
            }
        }
        return result;
    }

}
