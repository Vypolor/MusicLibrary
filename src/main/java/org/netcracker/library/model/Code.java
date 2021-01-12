package org.netcracker.library.model;

public enum Code {

    SUCCESSFUL(0, null),

    ADD_TRACK_TRUE(1, "track"),
    ADD_ALBUM_TRUE(2, "album"),
    ADD_SINGER_TRUE(3, "singer"),

    DELETE_TRACK_TRUE(11, "track"),
    DELETE_ALBUM_TRUE(12, "album"),
    DELETE_SINGER_TRUE(13, "singer"),

    ADD_TRACK_ERROR(110, "track"),
    ADD_ALBUM_ERROR(120, "album"),
    ADD_SINGER_ERROR(130,"singer"),

    TRACK_MISSING_FROM_LIBRARY(210, "track"),
    ALBUM_MISSING_FROM_LIBRARY(220, "album"),
    SINGER_MISSING_FROM_LIBRARY(230, "album"),

    EDIT_TRACK_ERROR(310, "track"),
    EDIT_ALBUM_ERROR(320, "album"),
    EDIT_SINGER_ERROR(330, "singer"),

    LOAD_ERROR(401 , null),
    SAVE_ERROR(402, null),
    SAVE_COMPLETE(403, null),
    INVALID_PATH(404, null),

    EMPTY_SEARCH(700, null),
    NOT_EMPTY_SEARCH(701, null),

    INVALID_KEY(500, null);

    private int codeNum;
    private String type;

    Code(int codeNum, String type)
    {
        this.codeNum = codeNum;
        this.type = type;
    }

    public int getCodeNum(){
        return codeNum;
    }

    public String getType(){ return type; }
}
