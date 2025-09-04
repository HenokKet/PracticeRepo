package org.example.repo;

import org.example.model.Album;
import org.example.model.Artist;

public class AlbumRepoInMemImpl implements AlbumRepo {
    private Album[] albums;
    private int maxAlbums = 20;
    private int nextAlbum = 0;

    public AlbumRepoInMemImpl(int maxAlbums) {
        this.maxAlbums = maxAlbums;
        albums = new Album[maxAlbums];
    }

    @Override
    public Album[] getAllAlbums() {
        Album[] result = new Album[nextAlbum];
        for (int i = 0; i < nextAlbum; i++) {
            result[i] = albums[i];
        }

        return result;
    }

    @Override
    public Album getAlbum(int id) {
        return albums[id];
    }

    @Override
    public Album[] getAlbumsByArtist(Artist artist) {
        // count the albums by artist
        int count = 0;
        for (int i = 0; i < nextAlbum; i++) {
            if (albums[i].getArtist().getId() == artist.getId()) {
                count++;
            }
        }

        // declare a new array and index
        Album[] results = new Album[count];
        int idx = 0;

        // loop through all albums and populate new array using idx
        for (int i = 0; i < nextAlbum; i++) {
            if (albums[i].getArtist().getId() == artist.getId()) {
                results[idx++] = albums[i];
            }
        }
        return results;
    }

    @Override
    public void addAlbum(Album album) {
        album.setId(nextAlbum);
        albums[nextAlbum] = album;
        nextAlbum++;
    }

    @Override
    public void updateAlbum(Album album) {
        albums[album.getId()] = album;
    }
}
