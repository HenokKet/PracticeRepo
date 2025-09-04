package org.example.repo;

import org.example.model.Album;
import org.example.model.Artist;

public interface AlbumRepo {
    Album[] getAllAlbums();
    Album getAlbum(int id);
    Album[] getAlbumsByArtist(Artist artist);
    void addAlbum(Album album);
    void updateAlbum(Album album);
}
