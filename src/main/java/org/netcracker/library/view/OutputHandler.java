package org.netcracker.library.view;

import org.netcracker.library.controller.InputHandler;
import org.netcracker.library.controller.MessageInformation;

import java.util.HashMap;
import java.util.Map;

public class OutputHandler {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public OutputHandler(){}

    public void generateMessage(MessageInformation information){
            int code = information.getCode().getCodeNum();

            //detailed information
            //============================================== add elements to the library ==============================================
            if (code != 0 && code < 10){
                StringBuffer addSb = new StringBuffer(ANSI_GREEN + "The ");
                //String complete = ANSI_GREEN + "The ";
                if(code == 3){
                    addSb.append("singer with name \"");
                    addSb.append(information.getSingerName());
                    addSb.append("\" successfully added to the library");
                }
                if(code == 2){
                    addSb.append("album with name \"");
                    addSb.append(information.getAlbumName());
                    addSb.append("\" has been successfully added to the artist's collection ");
                }
                if(code == 1){
                    addSb.append("track with name  \"");
                    addSb.append(information.getTrackName());
                    addSb.append("\" has been successfully added to the album with name");
                    addSb.append(information.getAlbumName());
                    addSb.append("\"");
                }
                addSb.append(ANSI_RESET);
                System.out.println(addSb);
            }

            //compact version
            /*if(code != 0 && code < 10){
                System.out.println(ANSI_GREEN + "The "
                        +
                        information.getCode().getType()
                        + " successfully added to the library! " + ANSI_RESET);
            }*/
            //============================================== delete item ==============================================================
            if (code > 10 && code < 20)
            {
                StringBuffer deleteCompleteSB = new StringBuffer(ANSI_GREEN);
                if(code == 11){
                    deleteCompleteSB.append("The track with name \"");
                    deleteCompleteSB.append(information.getTrackName());
                    deleteCompleteSB.append("\" successfully deleted");
                }

                if(code == 12){
                    deleteCompleteSB.append("The album with name \"");
                    deleteCompleteSB.append(information.getAlbumName());
                    deleteCompleteSB.append("\" successfully deleted");
                }

                if(code == 13){
                    deleteCompleteSB.append("The singer with name \"");
                    deleteCompleteSB.append(information.getSingerName());
                    deleteCompleteSB.append("\" successfully deleted");
                }
                deleteCompleteSB.append(ANSI_RESET);
                System.out.println(deleteCompleteSB);
            }

            //============================================== add item error ==========================================================

            if(code > 100 && code < 200){
                StringBuffer addItemErrorSB = new StringBuffer(ANSI_RED);
                if(code == 110){
                    addItemErrorSB.append("The track with name \"");
                    addItemErrorSB.append(information.getTrackName());
                    addItemErrorSB.append("\" is already in the album \"");
                    addItemErrorSB.append(information.getAlbumName());
                    addItemErrorSB.append("\"");
                }

                if(code == 120){
                    addItemErrorSB.append("The album with name \"");
                    addItemErrorSB.append(information.getAlbumName());
                    addItemErrorSB.append("\" is already in the \"");
                    addItemErrorSB.append(information.getSingerName());
                    addItemErrorSB.append("\" album collection");
                }

                if(code == 130){
                    addItemErrorSB.append("The singer with name \"");
                    addItemErrorSB.append(information.getSingerName());
                    addItemErrorSB.append("\" is already in the library");
                }
                addItemErrorSB.append(ANSI_RESET);
                System.out.println(addItemErrorSB);
            }

            //============================================== the item is missing from the library =====================================
            if (code > 200 && code < 300){
                StringBuffer itemMissingSB = new StringBuffer(ANSI_RED + "The ");
                if (code == 210){
                    itemMissingSB.append("track with the name \"");
                    itemMissingSB.append(information.getTrackName());
                    itemMissingSB.append("\" is not in the album \"");
                    itemMissingSB.append(information.getAlbumName());
                    itemMissingSB.append("\"");
                }
                if (code == 220){
                    itemMissingSB.append("album with the name \"");
                    itemMissingSB.append(information.getAlbumName());
                    itemMissingSB.append("\" is missing from the artist with name");
                    itemMissingSB.append(information.getSingerName());
                }
                if (code == 230){
                    itemMissingSB.append("album with the name \"");
                    itemMissingSB.append(information.getSingerName());
                    itemMissingSB.append("\" + is not in the library");
                }
                itemMissingSB.append("\nTry again");
                itemMissingSB.append(ANSI_RESET);
                System.out.println(itemMissingSB);
            }
            //============================================== search error ============================================================
            if(code == 500){
                String invalidKey = (ANSI_RED + "Invalid value of the key");
                System.out.println(invalidKey);
            }
            if(code == 700){
                String searchResult = (ANSI_RED + "No results were found for your search");
                System.out.println(searchResult);
            }
        }
}
