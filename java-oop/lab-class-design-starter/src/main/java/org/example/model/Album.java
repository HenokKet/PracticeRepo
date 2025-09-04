package org.example.model;

import java.util.Arrays;
import java.util.Objects;

public class Album {
    int id;
    private String title;
    private Artist artist;
    private Song[] songs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        return getId() == album.getId() && getTitle().equals(album.getTitle()) && getArtist().equals(album.getArtist()) && Arrays.equals(getSongs(), album.getSongs());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getTitle(), getArtist());
        result = 31 * result + Arrays.hashCode(getSongs());
        return result;
    }
}
