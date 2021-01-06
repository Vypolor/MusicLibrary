package org.netcracker.library;

import org.netcracker.library.controller.InputHandler;
import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;
import org.netcracker.library.util.RequestParser;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to music library");
        System.out.println("To add the track, enter /add");
        System.out.println("To delete the track, enter /delete");
        System.out.println("To find the track, enter /search");
        System.out.println("To look through the library, enter /show");
        System.out.println("To exit the library, enter /exit");
        System.out.println("Any questions? Enter /?");
        Album album0 = new Album("Savage Mode");

        Album album1 = new Album("Ballads 1");
        Album album2 = new Album("Nectar");

        //tracks for album 0
        Track intro = new Track("Intro", 1_10);
        Track intro2 = new Track("Intro", 1_12);
        Track runnin = new Track("Runnin", 3_15);
        Track gInMyLap = new Track("Glock In My Lap", 3_13);

        //tracks for album 1
        Track ew = new Track("Ew", 3_27);
        Track modus = new Track("MODUS?", 3_27);

        //tracks for album 2
        Track attention = new Track("ATTENTION", 2_08);
        Track testDrive = new Track("TEST DRIVE", 3_29);
        Track noFun = new Track("NO FUN", 2_48);

        //singers
        Singer savage21 = new Singer("21 Savage");
        Singer joji = new Singer("Joji");
        Singer youngThug = new Singer("Young Thug");


        album0.addTrack(intro);
        album0.addTrack(intro2);
        album0.addTrack(runnin);
        album0.addTrack(gInMyLap);
        album0.editTrack(intro, new Track("qq", 1_23));

        album1.addTrack(ew);
        album1.addTrack(modus);

        album2.addTrack(attention);
        album2.addTrack(testDrive);
        album2.addTrack(noFun);

        savage21.addAlbum(album0);
        joji.addAlbum(album1);
        joji.addAlbum(album2);

        Map<String, Singer> singers = new HashMap<>();
        singers.put(joji.getName(), joji);
        singers.put(savage21.getName(), savage21);

        Library main = new Library();
        main.setSingers(singers);
        main.setName("Test");
        ////////////////
        main.addSinger(youngThug);
        System.out.println(main.getSingers().size());
        System.out.println(main.toString());
        //System.out.println(album0.getTracks().size());

        InputHandler inputHandler = new InputHandler();

        Library test = Library.getInstance();
        test.setName(main.getName());
        test.setSingers(main.getSingers());

        System.out.println("A?S?D".replaceAll("\\?", "\\\\."));

        for (;;) {
            inputHandler.readRequest();
        }
    }
}
