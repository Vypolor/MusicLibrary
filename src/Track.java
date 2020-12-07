public class Track {

    private String name;
    private Album album;
    private long time;

    public Track(String name, long time) {
        this.name = name;
        this.time = time;
    }

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
    public String toString() {
        String result = new String(
                "\t\t==========================\n" +
                "\t\tTrack Name: " + getName() + "\n\t\tTrack Length: "+ getTime()
                + "\n"
                );
        return result;
    }
}
