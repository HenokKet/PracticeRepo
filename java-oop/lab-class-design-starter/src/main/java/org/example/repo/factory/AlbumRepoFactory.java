package org.example.repo.factory;

import org.example.model.Album;
import org.example.model.Song;
import org.example.repo.AlbumRepo;
import org.example.repo.AlbumRepoInMemImpl;
import org.example.repo.ArtistRepo;

public class AlbumRepoFactory {
    private static AlbumRepo instance = null;

    public static AlbumRepo instance() {
        if (instance == null) {
            instance = new AlbumRepoInMemImpl(20);

            ArtistRepo artists = ArtistRepoFactory.instance();

            Album album = new Album();
            album.setTitle("In FourDee");
            album.setArtist(artists.getArtist(0));
            Song[] songs = new Song[3];
            Song s = new Song();
            s.setId(1);
            s.setTitle("Scout Camp Hullabaloo");
            songs[0] = s;
            s = new Song();
            s.setId(2);
            s.setTitle("Nothing You Know Is Right");
            songs[1] = s;
            s = new Song();
            s.setId(3);
            s.setTitle("The All Consuming Clog Dance Of The Gods");
            songs[2] = s;
            album.setSongs(songs);
            instance.addAlbum(album);

            album = new Album();
            album.setTitle("Bookcases");
            album.setArtist(artists.getArtist(1));
            songs = new Song[3];

            s = new Song();
            s.setId(1);
            s.setTitle("The Sound of Quiet");
            songs[0] = s;
            s = new Song();
            s.setId(2);
            s.setTitle("Mrs. Robertson");
            songs[1] = s;
            s = new Song();
            s.setId(3);
            s.setTitle("The Pugilist");
            songs[2] = s;
            album.setSongs(songs);
            instance.addAlbum(album);

            album = new Album();
            album.setTitle("R4httown:  Level Infinity");
            album.setArtist(artists.getArtist(2));
            songs = new Song[3];
            s = new Song();
            s.setId(1);
            s.setTitle("Garble - Remix");
            songs[0] = s;
            s.setId(2);
            s = new Song();
            s.setTitle("Tsunami - Feat. Malick Famousdude");
            songs[1] = s;
            s = new Song();
            s.setId(3);
            s.setTitle("And Out");
            songs[2] = s;
            album.setSongs(songs);
            instance.addAlbum(album);

        }

        return instance;
    }
}
