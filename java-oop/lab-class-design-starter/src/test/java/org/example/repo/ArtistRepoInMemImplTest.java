package org.example.repo;

import org.example.model.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistRepoInMemImplTest {
    ArtistRepo repo;
    Artist artistOne;
    Artist artistTwo;

    @BeforeEach
    public void setup() {
        artistOne = new Artist();
        artistOne.setName("Alpha");
        artistOne.setId(1);

        artistTwo = new Artist();
        artistTwo.setName("Bravo");
        artistTwo.setId(2);

        repo = new ArtistRepoInMemImpl(3);
        repo.addArtist(artistOne);
        repo.addArtist(artistTwo);
    }

    @Test
    void getAllArtists() {
        int expectedCount = 2;

        Artist[] actual = repo.getAllArtists();
        assertEquals(expectedCount, actual.length);
        assertEquals(actual[0], artistOne);
        assertEquals(actual[1], artistTwo);
    }

    @Test
    void getArtist() {
        Artist actual = repo.getArtist(artistOne.getId());
        assertEquals(artistOne, actual);
    }
}