package org.example.repo.factory;

import org.example.model.Artist;
import org.example.repo.ArtistRepo;
import org.example.repo.ArtistRepoInMemImpl;

public class ArtistRepoFactory {
    private static ArtistRepo instance = null;

    public static ArtistRepo instance() {
        if (instance == null) {
            instance = new ArtistRepoInMemImpl(20);
            Artist artist = new Artist();
            artist.setName("'Crazy' Zoe Blankovic");
            instance.addArtist(artist);

            artist = new Artist();
            artist.setName("Simone and Glarewrinkle");
            instance.addArtist(artist);

            artist = new Artist();
            artist.setName("L1veR4ht");
            instance.addArtist(artist);
        }

        return instance;
    }
}
