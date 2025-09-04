package org.example.repo;

import org.example.model.Artist;

public interface ArtistRepo {
    Artist[] getAllArtists();
    Artist getArtist(int id);
    void addArtist(Artist artist);
    void updateArtist(Artist artist);
}
