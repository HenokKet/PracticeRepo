package org.example.repo;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Song;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumRepoInMemImplTest {
    AlbumRepoInMemImpl repo;
    Album testAlbumOne;
    Album testAlbumTwo;
    static Artist artistOne;
    static Artist artistTwo;

    @BeforeAll
    public static void buildArtists() {
        artistOne = new Artist();
        artistOne.setName("Alpha");
        artistOne.setId(1);

        artistTwo = new Artist();
        artistTwo.setName("Bravo");
        artistTwo.setId(2);
    }

    @BeforeEach
    public void setUp() {
        repo = new AlbumRepoInMemImpl(2);

        //Reset testAlbumOne
        testAlbumOne = new Album();
        testAlbumOne.setArtist(artistOne);
        testAlbumOne.setTitle("The First Album");
        Song[] songs = new Song[3];
        Song s = new Song();
        s.setId(0);
        s.setTitle("TFA - One");
        songs[0] = s;

        s = new Song();
        s.setId(1);
        s.setTitle("TFA - Two");
        songs[1] = s;

        s.setId(2);
        s.setTitle("TFA - Three");
        songs[2] = s;

        testAlbumOne.setSongs(songs);
        repo.addAlbum(testAlbumOne);

        //Reset testAlbumTwo
        testAlbumTwo = new Album();
        testAlbumTwo.setArtist(artistTwo);
        testAlbumTwo.setTitle("The Second Album");
        songs = new Song[3];
        s = new Song();
        s.setId(0);
        s.setTitle("TSA - A");
        songs[0] = s;

        s = new Song();
        s.setId(1);
        s.setTitle("TSA - B");
        songs[1] = s;

        s.setId(2);
        s.setTitle("TSA - C");
        songs[2] = s;

        testAlbumTwo.setSongs(songs);

        repo.addAlbum(testAlbumTwo);
    }

    @Test
    void getAllAlbums() {
        //Arrange
        int expectedCount = 2;

        //Act
        Album[] actual = repo.getAllAlbums();

        //Assert
        assertEquals(expectedCount, actual.length);
        assertEquals(testAlbumOne, actual[0]);
        assertEquals(testAlbumTwo, actual[1]);
    }

    @Test
    void getAlbum() {
        assertEquals(testAlbumOne, repo.getAlbum(testAlbumOne.getId()));
    }

    @Test
    void getAlbumsByArtist() {
        int expectedCount = 1;

        Album[] actual = repo.getAlbumsByArtist(artistOne);
        assertEquals(expectedCount, actual.length);
        assertEquals(testAlbumOne, actual[0]);
    }
}