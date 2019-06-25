package com.hbw.onepiece.entity.dbtable;

import org.litepal.crud.LitePalSupport;

public class Song extends LitePalSupport {

    private String SongName;
    private String SongNameToPinYin;

    public Song() {
    }

    public Song(String songName, String songNameToPinYin) {
        SongName = songName;
        SongNameToPinYin = songNameToPinYin;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getSongNameToPinYin() {
        return SongNameToPinYin;
    }

    public void setSongNameToPinYin(String songNameToPinYin) {
        SongNameToPinYin = songNameToPinYin;
    }

    @Override
    public String toString() {
        return "Song{" +
                "SongName='" + SongName + '\'' +
                ", SongNameToPinYin='" + SongNameToPinYin + '\'' +
                '}';
    }
}
