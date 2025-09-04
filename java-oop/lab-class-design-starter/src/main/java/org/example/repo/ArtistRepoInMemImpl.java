package org.example.repo;

import org.example.model.Artist;

public class ArtistRepoInMemImpl implements ArtistRepo {
    private Artist[] artists;
    private int maxArtists = 20;
    private int nextArtist = 0;

    public ArtistRepoInMemImpl(int maxArtists) {
        this.maxArtists = maxArtists;
        artists = new Artist[maxArtists];
    }

    @Override
    public Artist[] getAllArtists() {
        Artist[] result = new Artist[nextArtist];
        for (int i = 0; i < nextArtist; i++) {
            result[i] = artists[i];
        }

        return result;
    }

    @Override
    public Artist getArtist(int id) {
        return artists[id];
    }

    @Override
    public void addArtist(Artist artist) {
        artist.setId(nextArtist);
        artists[nextArtist] = artist;
        nextArtist++;
    }

    @Override
    public void updateArtist(Artist artist) {
        artists[artist.getId()] = artist;
    }


}
