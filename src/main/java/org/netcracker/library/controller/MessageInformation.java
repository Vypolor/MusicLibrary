package org.netcracker.library.controller;

import org.netcracker.library.model.Code;

public class MessageInformation {

    private String trackName;
    private String albumName;
    private String singerName;
    private String key;

    private Code code;

    public MessageInformation(Code code){
        this.code = code;
    }

    public MessageInformation(String key, Code code) {
        this.key = key;
        this.code = code;
    }

    public MessageInformation(String trackName, String albumName, String singerName, Code code) {
        this.trackName = trackName;
        this.albumName = albumName;
        this.singerName = singerName;
        this.code = code;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
