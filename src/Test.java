import java.util.HashSet;

public class Test {
    public static void main(String[] args) throws Exception {

        Album album0 = new Album("SAVAGE MODE ||");

        Album album1 = new Album("Ballads 1");
        Album album2 = new Album("Nectar");

        //tracks for album 0
        Track intro = new Track("Intro", 1_10);
        Track intro2 = new Track("Intro", 1_12);
        Track runnin = new Track("Runnin", 3_15);
        Track gInMyLap = new Track("Glock In My Lap", 3_13);

        //tracks for album 1
        Track ew = new Track("Ew", 3_27);
        Track modus = new Track("MODUS", 3_27);

        //tracks for album 2
        Track attention = new Track("ATTENTION", 2_08);
        Track testDrive = new Track("TEST DRIVE", 3_29);
        Track noFun = new Track("NO FUN", 2_48);

        //singers
        Singer savage21 = new Singer("21 Savage");
        Singer joji = new Singer("Joji");


        album0.addTrack(intro);
        album0.addTrack(runnin);
        album0.addTrack(gInMyLap);
        album0.editTrack(intro, "qq", 1_23);

        album1.addTrack(ew);
        album1.addTrack(modus);

        album2.addTrack(attention);
        album2.addTrack(testDrive);
        album2.addTrack(noFun);

        savage21.addAlbum(album0);
        joji.addAlbum(album1);
        joji.addAlbum(album2);

        HashSet<Singer> singers = new HashSet<>();
        singers.add(joji);
        singers.add(savage21);;

        Library main = new Library(singers);
        System.out.println(main.getSingers().toArray().length);
        System.out.println(main.toString());

    }
}
