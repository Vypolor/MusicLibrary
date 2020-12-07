import java.util.HashSet;
import java.util.Iterator;

public class Singer {

    private String name;
    private HashSet<Album> albums = new HashSet<Album>();

    public Singer(String name) {
        this.name = name;
    }

    public Singer(String name, HashSet<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public void addAlbum(Album addAlbum) throws Exception {
        if(albums.toArray().length == 0){
            albums.add(addAlbum);
        }
        //В случае если у исполнителя есть альбома
        //нужна проверка для сравнения имён для избежания дублирования
        else {
            Album [] albumArr = new Album[albums.toArray().length];
            albums.toArray(albumArr);
            for(int i = 0; i < albumArr.length; ++i){
                if(albumArr[i].getName().equals(addAlbum.getName())){
                    //В случае совпаденгия имен выбрасывается исключение
                    throw new Exception("the artist has an album with the name: " + addAlbum.getName());
                }
                else {
                    addAlbum.setSinger(this);
                    albums.add(addAlbum);
                }
            }
        }
    }

    //Для замены одного альбома другим
    public void editAlbum(Album editAlbum, Album newAlbum) throws Exception {
        deleteAlbum(editAlbum);
        addAlbum(newAlbum);
    }

    //Для изменения альбома
    public void editAlbum(Album editAlbum, String newName) throws Exception {
        //Запоминаем треки из старого альбома
        HashSet<Track> bucket = editAlbum.getTracks();
        //удаляем старый альбом
        deleteAlbum(editAlbum);

        //создаём новый альбом
        Album album = new Album(newName);
        //Устанавливаем ссылки старых треков на новый альбом
        Track[] tracksArr = new Track[bucket.toArray().length];
        bucket.toArray(tracksArr);
        for (int i = 0; i < tracksArr.length; ++i){
            tracksArr[i].setAlbum(album);
            album.addTrack(tracksArr[i]);
        }
        //Добавляем альбом
        addAlbum(album);
    }

    public void deleteAlbum(Album delAlbum){
        if(albums.contains(delAlbum)){
            delAlbum.setSinger(null);
            albums.remove(delAlbum);
        }
        //else System.out.println("The artist doesn't have this album");
    }

    public String getName() {
        return name;
    }

    public HashSet<Album> getAlbums() {
        return albums;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbums(HashSet<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        String result = new String( "=================================="+"\nSinger: " + getName());
        if(albums != null){
            for(Iterator<Album> iterator = albums.iterator(); iterator.hasNext();){
                result += iterator.next().toString();
            }
        }
        return result;
    }
}
