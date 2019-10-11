package com.kylin.models;

/**
 * Created by Kylin on 2018/4/8.
 */

public class MusicModel {
    /**
     * musicName : 东风破
     * musicSinger : 周杰伦
     * musicAlbum : 七里香
     * publicTime : 2007-10-02
     * classify : pop|Sinicism
     * more : 介绍
     * language : 国语
     */

    private String musicName;
    private String musicSinger;
    private String musicAlbum;
    private String publicTime;
    private String classify;
    private String more;
    private String language;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicSinger() {
        return musicSinger;
    }

    public void setMusicSinger(String musicSinger) {
        this.musicSinger = musicSinger;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "MusicModel{" +
                "musicName='" + musicName + '\'' +
                ", musicSinger='" + musicSinger + '\'' +
                ", musicAlbum='" + musicAlbum + '\'' +
                ", publicTime='" + publicTime + '\'' +
                ", classify='" + classify + '\'' +
                ", more='" + more + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
